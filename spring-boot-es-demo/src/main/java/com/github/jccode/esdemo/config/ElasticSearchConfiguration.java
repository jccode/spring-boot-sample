package com.github.jccode.esdemo.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ElasticSearchConfiguration
 *
 * @author 01372461
 */
@Configuration
public class ElasticSearchConfiguration {

    @Autowired
    private ElasticSearchConfigItem configItem;

    @Bean
    public RestHighLevelClient highLevelClient() {
        return new RestHighLevelClient(RestClient.builder(httpHosts()));
    }

    @Bean
    public RestClient restClient() {
        return RestClient.builder(httpHosts()).build();
    }

    private HttpHost[] httpHosts() {
        Integer port = configItem.getRestPort();
        return configItem.getIplist().stream().map(host -> new HttpHost(host, port)).toArray(HttpHost[]::new);
    }
}
