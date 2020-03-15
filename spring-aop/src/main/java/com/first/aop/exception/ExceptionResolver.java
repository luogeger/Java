package com.first.aop.exception;

import com.first.aop.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理，在WebLogAspectAOP之后执行，开启WebLogAspectAOP看不到日志输出
 */
@Slf4j
@Component
public class ExceptionResolver implements HandlerExceptionResolver {
    //private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.info("{} info-resolver", request.getRequestURI(), ex);

        ModelAndView mv = new ModelAndView(new MappingJackson2JsonView());
        Response result = new Response();
        result.setStatus(0);
        result.setCode("500");
        result.setMsg("接口异常，内部错误！");
        result.setData(ex.toString());
        mv.addObject(result);
        return mv;
    }
}
