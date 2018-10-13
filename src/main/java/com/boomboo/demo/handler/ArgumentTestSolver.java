package com.boomboo.demo.handler;

import com.boomboo.demo.annotation.ArgumentTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @author dapeng
 */
@Slf4j
public class ArgumentTestSolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        ArgumentTest requestParam = parameter.getParameterAnnotation(ArgumentTest.class);
        return (requestParam != null && StringUtils.hasText(requestParam.pattern()));
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        Assert.state(servletRequest != null, "No HttpServletRequest");
        ServletServerHttpRequest inputMessage = new ServletServerHttpRequest(servletRequest);
        //you can do anything here ,warn: stream can only read  once.

//        InputStream inputStream = inputMessage.getBody();
//        log.info("input:{}", inputStream.read());
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        return stringHttpMessageConverter.read(String.class, inputMessage);
    }
}
