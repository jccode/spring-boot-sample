package com.github.jccode.configurationpropertiesdemo;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "app")
public class MyAppConfigProperties {

    private Map<String, Integer> thresholds;

    private Integer count;

    private List<String> list;

    public Map<String, Integer> getThresholds() {
        return thresholds;
    }

    public void setThresholds(Map<String, Integer> thresholds) {
        this.thresholds = thresholds;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "MyAppConfigProperties{" +
                "thresholds=" + thresholds +
                ", count=" + count +
                ", list=" + list +
                '}';
    }
}
