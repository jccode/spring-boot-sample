package com.github.jccode.springcloud.integrateddemo.product.service;

import com.github.jccode.springbootsample.core.repo.CrudMapper;
import com.github.jccode.springbootsample.core.service.CrudServiceImpl;
import com.github.jccode.springcloud.integrateddemo.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends CrudServiceImpl<Product> {

    @Autowired
    public ProductService(CrudMapper<Product> mapper) {
        super(mapper);
    }
}
