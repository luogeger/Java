package com.first.aop.utils;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamUtils {

    /**
     * LOG
     */
    private static final Logger logger = LoggerFactory.getLogger(StreamUtils.class);
    
    /**
     * 根据指定字段过滤集合元素的数据
     *
     * @param sourceList
     * @param targetPropertySet
     * @return
     */
    public static List<Map<String, Object>> filterProperty(List<Map<String, Object>> sourceList, Set<String> targetPropertySet) {
        if (CollectionUtils.isEmpty(sourceList) || CollectionUtils.isEmpty(targetPropertySet)) {
            return null;
        }
        List<Map<String, Object>> resultList = new ArrayList<>();
        sourceList.stream().forEach(item ->
                {
                    Map<String, Object> newPropertyMap = new HashMap<>();
                    targetPropertySet.stream().forEach(property ->
                    {
                        // 剔除值为null的属性，避免JSON序列化时报错：HttpMessageNotWritableException
                        if (item.get(property) != null) {
                            newPropertyMap.put(property, item.get(property));
                        }
                    });
                    resultList.add(newPropertyMap);
                }
        );
        return resultList;
    }
    
    public static Map<String, Object> filterProperty(Map<String, Object> source, Set<String> targetPropertySet) {
        if (source == null || source.isEmpty() || CollectionUtils.isEmpty(targetPropertySet)) {
            return null;
        }
        Map<String, Object> target = new HashMap<>(8, 0.75f);
        targetPropertySet.stream().forEach(property -> {
            if (source.get(property) != null) {
                target.put(property, source.get(property));
            }
        });
        return target;
    }
    
    /**
     * 剔除请求中值为null的参数
     *
     * @param paramsMap
     * @return
     */
    public static Map<String, Object> removeNullElement(Map<String, Object> paramsMap) {
        if (paramsMap == null || paramsMap.isEmpty()) {
            return new HashMap<>(1, 0.5f);
        }
        /** 一般请求参数不会太多，故而使用单向顺序流即可 */
        
        // 1.首先构建流，剔除值为空的元素
        Stream<Map.Entry<String, Object>> tempStream = paramsMap.entrySet().stream()
                .filter(entry -> entry.getValue() != null);
        // 2.从流中恢复map
        Map<String, Object> resultMap = tempStream.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return resultMap;
    }
    
    /**
     * 从Map种移除类型为文件MultipartFile/File的元素
     * @param paramsMap
     * @return
     */
    public static Map<String, Object> removeFileValueElementForLog(Map<String, Object> paramsMap) {
        if (paramsMap == null || paramsMap.isEmpty()) {
            return paramsMap;
        }
        // 1.首先构建流，剔除指定元素
        Stream<Map.Entry<String, Object>> tempStream = paramsMap.entrySet().stream()
                .filter(entry -> {
                    if (entry.getValue() instanceof MultipartFile || entry.getValue() instanceof File) {
                        return false;
                    }
                    return true;
                });
        // 2.从流中恢复map
        Map<String, Object> resultMap = tempStream.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return resultMap;
    }
    
    /**
     * 从Map种移除指定类型的元素
     * @param paramsMap
     * @param clazzArray
     * @return
     */
    public static Map<String, Object> removeSpecifiedElement(Map<String, Object> paramsMap, Class<?>[] clazzArray) {
        if (Objects.isNull(paramsMap) || paramsMap.isEmpty()
                || Objects.isNull(clazzArray) || clazzArray.length == 0) {
            return paramsMap;
        }
        Map<String, Object> resultMap = new HashMap<>();
        // 将流导入Supplier工厂, 需要时即取出来, 取出来时就会构造流, 即新的实例
        Supplier<Stream<Class>> clazzStreamSupplier = () -> Arrays.stream(clazzArray);
        paramsMap.entrySet().stream()
                .filter(Objects::nonNull)
                .forEach(entry ->{
                    Stream<Class> clazzStream = clazzStreamSupplier.get();
                    // 若元素类型与目标类型相同, 则结束本次循环
                    // anyMatch属于流操作终止符, 因而每次操作前都需要获得流
                    if (clazzStream.anyMatch(clazz -> clazz.isInstance(entry.getValue()))) {
                        return;
                    }
                    resultMap.put(entry.getKey(), entry.getValue());
                });
        return resultMap;
    }
    
    /**
     * 从Map种移除指定类型的元素
     * @param paramArray
     * @param clazzArray
     * @return
     */
    public static Object[] removeSpecifiedElement(Object[] paramArray, Class<?>[] clazzArray) {
        if (Objects.isNull(paramArray) || paramArray.length == 0
                || Objects.isNull(clazzArray) || clazzArray.length == 0) {
            return paramArray;
        }
        List<Object> resultList = new ArrayList<>(paramArray.length);
        // 将流导入Supplier工厂, 需要时即取出来, 取出来时就会构造流, 即新的实例
        Supplier<Stream<Class>> clazzStreamSupplier = () -> Arrays.stream(clazzArray);
        Arrays.stream(paramArray)
                .filter(Objects::nonNull)
                .forEach(paramObj ->{
                    Stream<Class> clazzStream = clazzStreamSupplier.get();
                    // 若元素类型与目标类型相同, 则结束本次循环
                    // anyMatch属于流操作终止符, 因而每次操作前都需要获得流
                    if (clazzStream.anyMatch(clazz -> clazz.isInstance(paramObj))) {
                        return;
                    }
                    resultList.add(paramObj);
                });
        return resultList.toArray();
    }
    
    public static void main(String[] args) {
        String name = "name";
        String dataList = "dataList";
        List<Map<String, Object>> sourceList = new ArrayList<>(8);
        IntStream.range(1, 8)
                .forEach(item -> {
                    Map<String, Object> eleMap = new HashMap<String, Object>(4, 0.5f){
                        {
                            put(name, name);
                            put(dataList, new ArrayList<Map<String, Object>>(8) {
                                {
                                    add(new HashMap<String, Object>(2, 0.5f) {
                                        {
                                            put("key", "value");
                                        }
                                    });
                                }
                            });
                        }
                    };
                    sourceList.add(eleMap);
                });
        List<Map<String, Object>> resultList = new ArrayList<>(sourceList.size());
        IntStream.range(1, 8)
                .forEach(i -> {
                    resultList.add(new HashMap<String, Object>(4, 0.5f){
                        {
                            put(name, name);
                            put(dataList, new ArrayList<>());
                        }
                    });
                });
        resultList.stream()
                .filter(Objects::nonNull)
                .forEach(resultItem -> sourceList.stream()
                        .filter(Objects::nonNull)
                        .forEach(sourceItem -> {
                            if (sourceItem.get(name).equals(resultItem.get(name))) {
                                // 使用中间变量 避免强转
                                List<Map<String, Object>> tempList = JsonConvertUtil.parseList(resultItem.get(dataList));
                                tempList.add(sourceItem);
                                resultItem.put(dataList, tempList);
                            }
                        }));
        logger.info(JsonConvertUtil.formatStandardJSON(JSONObject.toJSONString(resultList)));
        
    }
}
