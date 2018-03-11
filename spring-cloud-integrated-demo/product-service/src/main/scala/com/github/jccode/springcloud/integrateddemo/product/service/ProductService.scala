package com.github.jccode.springcloud.integrateddemo.product.service

import com.github.jccode.springbootsample.core.service.CrudServiceImpl
import com.github.jccode.springcloud.integrateddemo.product.repo.ProductMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService(@Autowired override val mapper: ProductMapper) extends CrudServiceImpl(mapper){

}
