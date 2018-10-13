package com.boomboo.demo.service.urlstatistics;

import com.boomboo.demo.event.UrlStatisticsEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class UrlStatisticsPublisher {

    @Autowired
    private ApplicationEventPublisher publisher;

    public UrlStatisticsEvent publishDelayOrder(String methodName, String requestUrl) {
        final UrlStatisticsEvent urlStatisticsEvent = new UrlStatisticsEvent(this, methodName, requestUrl);
        publisher.publishEvent(urlStatisticsEvent);
        return urlStatisticsEvent;
    }

}
