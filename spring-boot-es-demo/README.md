
Overview
========

Elasticsearch demo & intergration test.


Test
====

    mvn test -Dtests.security.manager=false

Reference:
- [Issue workaround](https://github.com/elastic/elasticsearch/issues/22689 ) 
- ["Unable to install test security manager" discussion ](https://discuss.elastic.co/t/unable-to-install-test-security-manager-when-using-estestcase/150031/3 ) 



FAQ
===

NoClassDefFoundError
---------------------

Errors as follows:

     java.lang.NoClassDefFoundError: Could not initialize class org.elasticsearch.test.ESTestCase

这个一般是包冲突.需要在`pom.xml`中`exclude`冲突的jar包.
主要包括:
- `commons-logging`
- `hamcrest-core`


Duplicated jar idea_rt.jar in Intelij IDEA
------------------------------------------

在Intelij中运行测试用例,也会遇到`JAR hell` error,说`idea_rt.jar`重复了.

需要添加 `-Didea.no.launcher=true` 到 `VM Option` 中去.

Intelij IDEA `Help -> Edit Custom VM Option`, 然后添加上面那一行.

[Reference](https://github.com/elastic/elasticsearch/blob/master/CONTRIBUTING.md ) 


elasticsearch-maven-plugin
--------------------------

这个插件在`maven test`时会启动一个elasticsearch server.
但这个插件似乎只在你的测试用例继续`ESIntegTestCase`类时,才会启动.


