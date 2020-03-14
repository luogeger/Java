package com.first.aop.aop;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author luoxiaoqing
 * @date 2011-02-22__15:56
 */
@Aspect//切面编程注解
@Order(1)//定义优先级，value越小 优先级越高
@Component
public class NoRepeatSubmitAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private final static String CACHE_PREFIX = "AOP_SUBMIT_";


    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @PostConstruct
    public void init() {
        logger.info("Url拦截切面初始化");
    }

    /**
     * 对Controller的Action操作在某时间段内进行限制操作次数
     * <p>
     * value里面的值就是我们url拦截注解的包路径
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around(value = "@annotation(com.first.aop.aop.NoRepeatSubmit)")
    public Object intercept(ProceedingJoinPoint pjp) throws Throwable {
        Object obj = null;

        //  session
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String sessionId = request.getSession().getId();

        //  method
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();


        //  获取到类的RequestMapping
        RequestMapping classRequestMapping = pjp.getTarget().getClass().getAnnotation(RequestMapping.class);
        //  类上RequestMapping的value值
        String[] classKeys = classRequestMapping.value();


        //  获取方法上的RequestMapping
        RequestMapping methodRequestMapping = method.getAnnotation(RequestMapping.class);
        //  方法上RequestMapping的value值
        String[] methodKeys = methodRequestMapping.value();

        //  获取方法参数
        Object[] params = pjp.getArgs();

        //  存放到redis
        String redisKey = CACHE_PREFIX + classKeys[0] + methodKeys[0] + "_" + sessionId;
        String redisVal = JSONObject.toJSONString(params[0]);


        //  1. 先查询
        String value = stringRedisTemplate.opsForValue().get(redisKey);

        //  key不存在。或key存在，但是参数值不相等，才能再设置
        boolean flag = value == null || !value.equals(redisVal);
        if (flag) {
            //  2. 在设置
            stringRedisTemplate.opsForValue().set(redisKey, redisVal, 10, TimeUnit.SECONDS);
            logger.info("请求数据：url={}, params={}", classKeys[0] + methodKeys[0], redisVal);
            obj = pjp.proceed();
        } else {
            //这里表示缓存存在，那么我们就返回一个方法的新实例。
            obj = method.getReturnType().getConstructor().newInstance();
            logger.info("频繁操作 >>>> url={}, params={}", classKeys[0] + methodKeys[0], redisVal);
        }

        //redisUtils.setIfAbsent返回一个boolean值，如果缓存设置成功（也就是之前对应的缓存不存在），我们就进行后续操作。调用pjp.proceed()
        //然后以我们的CACHE_PREFIX+类和方法的RequestMapping的value值（也就是我们的请求路径后缀，确定唯一）作为key存入redis，值随便存点什么，这里存了个1，这是redis key的有效时间为我们NoRepeatSubmit注解设置的时间。
        return obj;
    }


}

