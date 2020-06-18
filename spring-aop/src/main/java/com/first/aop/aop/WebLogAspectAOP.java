
package com.first.aop.aop;

import com.alibaba.fastjson.JSONObject;
import com.first.aop.utils.*;
import com.google.common.collect.Maps;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * 统一日志记录
 */
@Aspect
@Component
public class WebLogAspectAOP {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut(value = "execution(public * com.first.aop.controller..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        // 开始时间
        startTime.set(System.currentTimeMillis());

        // 请求参数
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String requestParam = "";

        // 1.请求接口
        String requestInterface = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();

        // 2.Param参数 - 入参为文件时, 不打印log
        Map originRequestParamMap = request.getParameterMap();
        if (originRequestParamMap.size() > 0) {
            Map<String, Object> filteredFileValueMap =
                    StreamUtils.removeSpecifiedElement(originRequestParamMap, new Class[]{MultipartFile.class, File.class});
            requestParam = JSONObject.toJSONString(filteredFileValueMap);
        }


        // 3.Body参数 - 入参为文件时, 不打印log
        Object[] originBodyParamArray = joinPoint.getArgs();
        Object[] filteredFileValueArray = StreamUtils.removeSpecifiedElement(originBodyParamArray, new Class[]{MultipartFile.class, File.class});

        Map requestBody = Maps.newHashMap();
        if (filteredFileValueArray.length >= 1) {
            if (filteredFileValueArray[0] instanceof HttpServletRequest) {
                //
            } else if (filteredFileValueArray[0] instanceof StandardMultipartHttpServletRequest) {
                requestBody = originRequestParamMap;
            } else {
                Map map = JSONObject.parseObject(JSONObject.toJSONString(filteredFileValueArray[0]));
                requestBody = StreamUtils.removeNullElement(map);
            }
        }

        StringBuilder requestSb = new StringBuilder();
        requestSb.append("\nRequestInfo:\n"
                + "ip=" + getIpAddress(request)
                //+ ",url=" + request.getRequestURL().toString()
                + ",method=" + request.getMethod()
                + ",header=" + request.getHeader("Authorization")
                //+ ",thread=" + Thread.currentThread().getName()
                + ",interface=" + requestInterface
                + ",param=" + requestParam
                + ",body=");
        requestSb.append(JSONObject.toJSONString(requestBody));
        logger.info(requestSb.toString());
    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();
        Object obj = null;
        try {
            obj = pjp.proceed();
            logger.info("time : {}", (System.currentTimeMillis() - startTime));
        } catch (Throwable throwable) {
            obj = handlerException(pjp, throwable);
        }
        return obj;
    }

    @AfterReturning(value = "webLog()", returning = "returnVal")
    public void doAfterReturning(JoinPoint joinPoint, Object returnVal) {
        // 处理完请求，返回内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String requestInterface = joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName();
        long execTime = System.currentTimeMillis() - startTime.get();
        StringBuilder responseSb = new StringBuilder();
        responseSb.append("\nResponseInfo: "
                //+ "url = " + request.getRequestURL().toString() + "\n"
                //+ "interface = " + requestInterface + "\n"
                //+ "time = " + execTime + "ms" + "\n"
        );
        responseSb.append(JSONObject.toJSONString(returnVal));
        logger.info(responseSb.toString());
        startTime.remove();
    }

    /**
     * 获取真实IP
     *
     * @param request
     * @return
     */
    private String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if ("127.0.0.1".equals(ipAddress) || "0:0:0:0:0:0:0:1".equals(ipAddress)) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    logger.error("获取ip异常 : {}", e);
                }
                ipAddress = inet.getHostAddress();
            }
        }
        if (ipAddress != null && ipAddress.length() > 15) {
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    /**
     * 异常处理
     *
     * @param pjp
     * @param ex
     * @return
     */
    private Response handlerException(JoinPoint pjp, Throwable ex) {
        Response response = new Response();
        response.setStatus(0);
        Object[] args = pjp.getArgs();
        StringBuilder sb = new StringBuilder();
        for (Object arg : args) {
            sb.append(arg);
        }
        logger.info("参数 : {}", sb.toString());
        if (ex instanceof BaseException) {
            BaseException e = (BaseException) ex;
            response.setCode(e.getErrCode());
            response.setMsg(e.getMessage());
            logger.error("BaseException==>方法：{}，参数：{}，异常：{}", pjp.getSignature(), sb.toString(), e.getMessage());
        } else if (ex instanceof BusinessException) {
            BusinessException e = (BusinessException) ex;
            response.setCode(e.getErrCode());
            response.setMsg(e.getMessage());
            logger.error("BusinessException==>方法：{}，参数：{}，异常：{}", pjp.getSignature(), sb.toString(), ex);
        } else if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException e = (MethodArgumentNotValidException) ex;
            logger.info(e.getCause().getMessage());
        }
        else {
            response.setCode(SystemStatus.SERVER_ERROR_CODE.getCode());
            response.setMsg(ex.getMessage());
            logger.error("异常==>方法：{}，参数：{}，异常：{}", pjp.getSignature(), sb.toString(), ex);
        }
        return response;
    }
}
