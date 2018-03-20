package com.github.jccode.springbootrestintegrateddemo.service

import com.github.jccode.springbootrestintegrateddemo.model.Product
import com.github.jccode.springbootsample.core.repo.CrudMapper
import com.github.jccode.springbootsample.core.service.CrudServiceImpl
import org.springframework.beans.factory.annotation.Autowired

/**
  * ProductService
  *
  * @author 01372461
  */
@Autowired
class ProductService(@Autowired override val mapper: CrudMapper[Product]) extends CrudServiceImpl[Product](mapper) {

}
