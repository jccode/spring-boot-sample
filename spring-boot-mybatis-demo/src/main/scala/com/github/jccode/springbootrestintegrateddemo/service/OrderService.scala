package com.github.jccode.springbootrestintegrateddemo.service

import com.github.jccode.springbootrestintegrateddemo.model.{Order, OrderAndUser, OrderCriteria, OrderExt}
import com.github.jccode.springbootrestintegrateddemo.repo.OrderMapper
import com.github.jccode.springbootsample.core.service.CrudServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import scala.collection.JavaConverters._

/**
  * OrderService
  *
  * @author 01372461
  */
@Service
class OrderService(@Autowired override val mapper: OrderMapper) extends CrudServiceImpl[Order](mapper) {

  def findOrderAndUsers(example: OrderCriteria): Seq[OrderAndUser] = mapper.findOrderAndUsers(example).asScala

  def findOrderExt(example: OrderCriteria): Seq[OrderExt] = mapper.findOrderExt(example).asScala
}
