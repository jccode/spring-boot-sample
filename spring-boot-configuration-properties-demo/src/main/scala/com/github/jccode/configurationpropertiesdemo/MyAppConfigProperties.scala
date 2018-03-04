package com.github.jccode.configurationpropertiesdemo

import org.springframework.boot.context.properties.ConfigurationProperties

import scala.beans.BeanProperty


@ConfigurationProperties(prefix = "app")
class MyAppConfigProperties {

  @BeanProperty var thresholds: java.util.Map[String, Integer] = null

  @BeanProperty var count: Integer = null

  @BeanProperty var list: java.util.List[String] = null

  override def toString = s"MyAppConfigProperties($thresholds, $count, $list)"

}
