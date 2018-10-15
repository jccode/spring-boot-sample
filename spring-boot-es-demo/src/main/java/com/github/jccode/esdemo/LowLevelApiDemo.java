package com.github.jccode.esdemo;

import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * LowLevelApiDemo
 *
 * @author 01372461
 */
@Component
public class LowLevelApiDemo {

    @Autowired
    private RestClient client;

    public void demo() {
        System.out.println("Low level client "+client);
    }
}
