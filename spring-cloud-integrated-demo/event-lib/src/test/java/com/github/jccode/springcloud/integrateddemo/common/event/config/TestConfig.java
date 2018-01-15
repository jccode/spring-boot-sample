package com.github.jccode.springcloud.integrateddemo.common.event.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringBootConfiguration;

@SpringBootConfiguration
@MapperScan("com.github.jccode.springcloud.integrateddemo.**.*.repo")
public class TestConfig {
}
