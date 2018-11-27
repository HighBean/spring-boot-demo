package com.boomboo.demo.service.wrapper;

import com.alibaba.fastjson.JSONObject;
import com.boomboo.demo.service.ElasticSearchService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AServiceAImpl extends AbstractAService {

    @Resource
    private ElasticSearchService elasticSearchService;

    @Override
    public void executeA() {
        elasticSearchService.postData("", new JSONObject(), "");
        System.out.println("AImpl");
    }

    @Override
    public boolean support(String str) {
        return "A".equals(str);
    }
}
