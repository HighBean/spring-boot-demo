package com.boomboo.demo.java.SelfAnnotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by dapeng on 2017/6/22.
 */
@Aspect
@Component
public class AnnotationAspect {
    Logger log = LoggerFactory.getLogger(AnnotationAspect.class);

    @Pointcut("@annotation(com.boomboo.demo.java.SelfAnnotation.Insert)")
    public void annotationAspect() {
    }
//
//    @Before("annotationAspect()")
//    public void handlerCut(JoinPoint joinPoint) {
//        Object[] args = joinPoint.getArgs();
//        ZtxCommunicationVo ztxCommunicationVo = (ZtxCommunicationVo) args[0];
//        checkSign(ztxCommunicationVo);
//    }
//
//    private boolean checkSign(ZtxCommunicationVo ztxCommunicationVo) {
//        String data = ztxCommunicationVo.getData();
//        String sign = ztxCommunicationVo.getSign();
//        log.info("data:{},sign:{}", data, sign);
//        return true;
//    }
//
//    @Around("annotationAspect()")
//    public Object handlerAnnotation(ProceedingJoinPoint joinPoint) throws OrderNotFoundException, ZtxServerErrorException {
//        //获取value内容
//        Object joinObj = joinPoint.getTarget().getClass();
//        Method[] methods = joinObj.getClass().getDeclaredMethods();
//        String annotationValue = "";
//        for (Method method : methods) {
//            if (method.isAnnotationPresent(Insert.class)) {
//                annotationValue = method.getAnnotation(Insert.class).value();
//            }
//        }
//        Object[] args = joinPoint.getArgs();
//        ZtxCommunicationVo ztxCommunicationVo = (ZtxCommunicationVo) args[0];
//        if (!checkSign(ztxCommunicationVo)) {
//            throw new OrderNotFoundException("123完蛋啦");
//        }
//        try {
//            Object o = joinPoint.proceed(args);
//            return o;
//        } catch (Throwable throwable) {
//            log.error("执行方法时出错ex：{}", throwable);
//            throw new ZtxServerErrorException("执行方法时出错");
//        }
//    }


}
