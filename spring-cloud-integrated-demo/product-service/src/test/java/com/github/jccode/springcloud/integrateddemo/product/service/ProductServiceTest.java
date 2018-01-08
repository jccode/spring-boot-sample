package com.github.jccode.springcloud.integrateddemo.product.service;


import com.github.jccode.springcloud.integrateddemo.product.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

@SpringBootTest
@RunWith(SpringRunner.class)
@MybatisTest
@Import(ProductService.class)
@ActiveProfiles("test")
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void mybatisWorks() {

        Product product = new Product();
        product.setName("iphone X");
        product.setPrice(6957);
        product.setStock(0);
        assertThat(product.getId(), is(nullValue()));

        int result = productService.save(product);
        assertThat(result, greaterThan(0));
        assertThat(product.getId(), greaterThan(0));
    }
}
