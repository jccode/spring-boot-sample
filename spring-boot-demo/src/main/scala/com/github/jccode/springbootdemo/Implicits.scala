package com.github.jccode.springbootdemo

import org.springframework.boot.CommandLineRunner

object Implicits {

  implicit def funToCommandLineRunner(fun: Seq[String] => Unit): CommandLineRunner = new CommandLineRunner {
    override def run(args: String*): Unit = fun(args)
  }

}
