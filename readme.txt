功能列表
	1、常用过滤器。
	2、logback配置。
	3、回声测试servlet。
	4、dubbo demo-consumer。
	5、hessian demo-client。
	6、redis 分布式会话。
	7、redis 读写demo。
	8、表单防篡改。
	9、配置中心客户端。
	10、findbugs。
	11、checkstyle。
	12、工具类。

详细说明(如果是 部署到weblogic 上面 将 pom.xml 里面设置的编译版本1.7改为1.6)
	1、常用过滤器。
		基本环境配置
			a)在web.xml配置filterChainProxy。
			b)在application.xml中增加sfpay-mvc:config。
		注意事项：需引入sfpay-mvc名称空间。
	==================================================================================
	2、logback配置
		 基本环境配置	
			a)需引入logback 1.1.2版本的jar包，包括logback-core和logback-classic，将低版本的exclusion。
			b)需引入jcl-over-slf4j.jar代替commons-logging、引入log4j-over-slf4j.jar代替log4j、引入jul-to-slf4j.jar代替jdk logging。
		 注意事项
			a)需将直接或间接引用的以下包移除或exclusion：
				1)commons-logging
				2)log4j
				3)slf4j-log4j12
			b)如果引入引入jul-to-slf4j，则需要配置以下bean进行初始化：
				com.sfpay.framework2.common.util.LogbackJdkLogAdapter
	==================================================================================
	3、回声测试servlet
		基本环境配置	
			a)在web.xml中增加com.sfpay.framework2.web.servlet.EchoServlet，具体见web.xml。
		注意事项
			a)需要framework2-1.3.8及以上版本。
			b)注意不要被过滤器拦截，例如系统使用shiro时，需配置“/echo = anon”。
	==================================================================================	
	4、dubbo demo-consumer
		基本环境配置
			a)引入framework2-dubbo-xxx.jar。
			b)配置dubbo bean，具体见src/test/resources/test/dubbo/beans/beans-dubbo.xml。
			c)在application.xml引入dubbo的配置。
		注意事项
			a)此示例只是dubbo的客户端，如要运行完整的例子，可构建archetype-server-archetype工程。
			b)如果不需要使用dubbo，请移除基本环节配置。
			c)更详细的配置可以参考http://wiki.sf-pay.com中“framework2使用指南”以及dubbo的官方网站：http://dubbo.io。
	==================================================================================
	5、hessian demo-client
		基本环境配置
			a)引入framework2-web。
			b)增加hessian的bean配置，具体见/airscape-server/src/test/resources/test/hessian/beans/beans-hessian.xml
			c)在application.xml引入hessian的配置。
		注意事项
			a)此示例只是hessian客户端，如要运行完整的例子，可构建archetype-server-archetype工程。
	==================================================================================
	6、redis 分布式会话	
		基本环境配置
			a)增加redisspring配置，具体见src/main/resources/beans/beans-redisSession.xml。
			b)增加分布式会话过滤器，具体见application.xml的sfpay-mvc:config配置。
			c)在application.xml引入beans-redisSession.xml。
		注意事项
			a)如果项目中已经使用了其它权限框架，如shiro，请慎用reids分布式会话。
	==================================================================================
	7、redis 读写demo
		基本环境配置
			a)增加redisTemplate配置，具体见src/test/resources/test/redis/beans/beans-redis.xml
			b)读写例子见src/test/java/com/sfpay/airscape/redis/RedisTest.java
		注意事项
			a)避免与其它项目和本项目中的key重复。
	==================================================================================	
	8、表单防篡改。
		基本环境配置
			a)引入framework2-security
			b)注解拦截 <aop:aspectj-autoproxy />
			c)页面处理
				1)引入标签
					<%@ taglib prefix="sfpay" uri="http://www.sf-pay.com/tags" %>
				2)表单增加sfpay:formToken和sfpay:formParam标签，具体见src/main/webapp/WEB-INF/view/insert.jsp
				3)controller中加入@Form，具体见src/test/java/com/sfpay/airscape/mvc/web/OrderController.java
	更详细的配置可以参考http://wiki.sf-pay.com中“framework2使用指南

	==================================================================================
	9、配置中心客户端
		基本环境配置
			a)引入framework2-context-xxx.jar
			b)在spring中配置com.sfpay.framework2.remote.zookeeper.configuration.ZookeeperConfigurerPostProcessor
			  具体见src/test/resources/test/cmc/beans/beans-cmc.xml
			c)要定义管理配置项KEY的枚举ConfigKey，具体见src/test/java/com/sfpay/airscape/cmc/ConfigKeyEnum.java
			d)在application.xml引入beans-cmc.xml。
			e)获取指定key的值： ConfigurerHolder.get(ConfigKeyEnum.IMG_SAVE_PATH);
		注意事项
			a)避免与其它项目和本项目中的key重复。
			
	==================================================================================
	10、findbugs
		基本环境配置
			a)增加findbugs-exclude.xml配置，具体见工程。
	==================================================================================
	11、checkstyle
		基本环境配置
			a)增加sf-checkstyle.xml配置，具体见工程。
	==================================================================================
	12、工具类
		具体见src/test/java/com/sfpay/airscape/util/TestUtil.java，包括以下工具类的基本功能演示
			framework2-core: 
				AssertUtils 断言工具类 
				JsonUtil json工具类 
				DateUtil 时间工具类
				Md5Utils MD5加密算法
				FileUtil 文件操作类
				CurrencyType 货币
				WebUtil web层工具类
				WebJsonUtil web层使用的json工具类 
			framework2-security:
				SecurityUtil 框架的安全工具类
				SessionUtil 框架会话的工具类
			framework2-web:
				SerializeUtil 序列化工具
