package com.boomboo.demo.service.wrapper;

import com.alibaba.fastjson.JSONObject;
import com.boomboo.demo.service.ElasticSearchService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AServiceBImpl extends AbstractAService {

    @Resource
    private ElasticSearchService elasticSearchService;

    @Override
    public void executeA() {
        elasticSearchService.postData("", new JSONObject(), "");
        System.out.println("BServiceImpl");
    }

    @Override
    public boolean support(String str) {
        return "B".equals(str);
    }
}
