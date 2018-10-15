package com.github.jccode.esdemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ElasticSearchConfiguration
 *
 * @author 01372461
 */
@Component
@Data
@ConfigurationProperties(prefix = "elasticsearch")
public class ElasticSearchConfigItem {

    private List<String> iplist;

    private Integer port;

    private Integer restPort;

    private String pool;

    private String clusterName;
}
