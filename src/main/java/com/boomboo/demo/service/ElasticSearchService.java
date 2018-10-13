package com.boomboo.demo.service;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service("elasticSearchService")
public class ElasticSearchService {

    private static RestClient client = null;

    private static final Integer ES_TIME_OUT = 20000;

    private static final String esHostUrl = "127.0.0.1:9200";

    @PostConstruct
    private void initialRestClient() {
        String[] hostUrls = esHostUrl.split(";");
        List<HttpHost> httpHostList = new ArrayList(hostUrls.length);
        for (String hostUrl : hostUrls) {
            httpHostList.add(HttpHost.create(hostUrl));
        }
        client = RestClient.builder(httpHostList.toArray(new HttpHost[httpHostList.size()])).setMaxRetryTimeoutMillis(ES_TIME_OUT).build();
    }

    public void postData(String index, JSONObject data, String bussinessCode) {
        try {
            String requestUrl = new StringBuilder().append("/").append(index)
                    .append("/").append("type").append("/").append(bussinessCode).toString();
            HttpEntity entity = new NStringEntity(data.toJSONString(), ContentType.APPLICATION_JSON);
            Response resp = client.performRequest("POST", requestUrl, Collections.EMPTY_MAP, entity, new BasicHeader("a", "b"));
        } catch (Exception ex) {
            log.error("esService postData error", ex);
        }
    }
}
