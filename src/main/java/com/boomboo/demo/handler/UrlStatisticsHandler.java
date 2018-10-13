package com.boomboo.demo.handler;


import com.boomboo.demo.service.urlstatistics.UrlStatisticsPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

@Component
@Slf4j
public class UrlStatisticsHandler {

    @Resource
    RequestMappingHandlerMapping handlerMapping;

    @Resource
    UrlStatisticsPublisher publisher;

    public void statistics(HttpServletRequest request) {
        HandlerMethod handlerMethod;
        try {
            HandlerExecutionChain handler = handlerMapping.getHandler(request);
            handlerMethod = (HandlerMethod) handler.getHandler();
        } catch (Exception e) {
            log.info("getHandler error", e);
            return;
        }
        Method method = handlerMethod.getMethod();
        publisher.publishDelayOrder(serilizeMethod(method), request.getRequestURI());
    }


    private String serilizeMethod(Method method) {
        StringBuilder paramStr = new StringBuilder();
        for (Type param : method.getGenericParameterTypes()) {
            paramStr.append(param.getTypeName());
        }
        return method.getDeclaringClass().getTypeName() + ":" + method.getName() + ":" + paramStr;
    }

}
