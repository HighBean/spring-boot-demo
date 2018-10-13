package com.boomboo.demo.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * @author boomboo
 */
public class UrlStatisticsEvent extends ApplicationEvent {

    @Getter
    @Setter
    private String serializedMethodName;

    @Getter
    @Setter
    private String requestUrl;


    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public UrlStatisticsEvent(Object source, String serializedMethodName, String requestUrl) {
        super(source);
        this.requestUrl = requestUrl;
        this.serializedMethodName = serializedMethodName;
    }

    @Override
    public String toString() {
        return "UrlStatisticsEvent{" +
                "serializedMethodName='" + serializedMethodName + '\'' +
                ", requestUrl='" + requestUrl + '\'' +
                '}';
    }
}
