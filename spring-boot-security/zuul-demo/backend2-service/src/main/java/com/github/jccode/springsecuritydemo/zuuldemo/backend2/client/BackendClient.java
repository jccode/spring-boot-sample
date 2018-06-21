package com.github.jccode.springsecuritydemo.zuuldemo.backend2.client;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Backend2Client
 *
 * @author 01372461
 */
@FeignClient(name = "backend", fallback = BackendClient.BackendClientFallback.class)
public interface BackendClient {

    @RequestMapping("/book/list")
    List<String> bookList();

    @Slf4j
    @Component
    class BackendClientFallback implements BackendClient {

        @Override
        public List<String> bookList() {
            log.info("[Fallback] Failed to get book list.");
            return Lists.newArrayList();
        }
    }
}
