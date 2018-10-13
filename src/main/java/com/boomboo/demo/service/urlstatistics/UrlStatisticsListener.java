package com.boomboo.demo.service.urlstatistics;

import com.alibaba.fastjson.JSONObject;
import com.boomboo.demo.event.UrlStatisticsEvent;
import com.boomboo.demo.service.ElasticSearchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author dapeng
 */
@Slf4j
@Service
public class UrlStatisticsListener implements ApplicationListener<UrlStatisticsEvent> {

    @Resource
    private ElasticSearchService elasticSearchService;

    private static ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onApplicationEvent(UrlStatisticsEvent event) {
        //save url statistics  by ElasticSearch
        String bussinessCode = event.getSerializedMethodName() + System.currentTimeMillis();
        log.info("bussinessCode:{}", bussinessCode);
        elasticSearchService.postData("url_statistics", toJSONObject(event), bussinessCode);
    }

    public static JSONObject toJSONObject(Object obj) {
        return mapper.convertValue(obj, JSONObject.class);
    }
}
