#视图解析器常见功能、处理静态资源、类相关转换器(P8)
##之前内容的概述:
>InternalResourceViewResolver其他功能：  
1.<mvc:view-controller ...>  
index.jsp -> Controller(@RequsetMapping("a")) ->succes.jsp

## 省略Controller(@RequsetMapping("a"))
>要用SpringMVC实现：index.jsp -> succes.jsp:  
`<mvc:view-controller path="a"   view-name="success" />`  
以上注解 ，会让所有的请求 转入<mvc:..>中匹配映射地址，而会忽略调@RequsetMapping()；
如果想让 @RequsetMapping("a")  和<mvc:..>共存，则需要加入一个注解：<mvc:annotation-driven></mvc:annotation-driven>

2.指定请求方式

指定跳转方式：return "forward:/views/success.jsp";

forward:   redirect: ，需要注意 此种方式，不会被视图解析器加上前缀(/views)、后缀(.jsp)

3.处理静态资源：html css js  图片 视频

可以与用户交互、因为时间/地点的不同 而结果不同的内容：动态（百度：天气  ）


在SpringMVC中，如果直接访问静态资源：404 。原因：之前将所有的请求 通过通配符“、” 拦截，进而交给 SPringMVC的入口DispatcherServlet去处理：找该请求映射对应的 @requestMapping

http://localhost:8888/SpringMVCProject/img.png

@RequsetMapping("img.png")
return sucess


解决：如果是 需要mvc处理的，则交给@RequsetMapping("img.png")处理；如果不需要springmvc处理，则使用 tomcat默认的Servlet去处理。
tomcat默认的Servlet去处理：如果有 对应的请求拦截,则交给相应的Servlet去处理；如果没有对应的servlet，则直接访问。
tomcat默认的Servlet在哪里？在tomcat配置文件\conf\web.xml中

	<servlet>
		<servlet-name>abc</servlet-name>
		<servlet-class>xxx.xxx.xx.ABCServlet</servlet-class>
	</servlet>
	
	<servlet-mapping>
		<servlet-name>abc</servlet-name>
		<url-pattern>/abc</url-pattern>
	</servlet-mapping>


解决静态资源方案：如果有springmvc对应的@requestMapping则交给spring处理；如果没有对应@requestMapping,则交给服务器tomcat默认的servlet去处理  ：实现方法，只需要增加2个注解即可 springmvc.xml：
<mvc:default-servlet-handler></mvc:default-servlet-handler>
<mvc:annotation-driven></mvc:annotation-driven>


总结：要让springmvc访问静态资源，只需要加入以下2个注解：

<mvc:default-servlet-handler></mvc:default-servlet-handler>
<mvc:annotation-driven></mvc:annotation-driven>


4.类型转换

a.Spring自带一些 常见的类型转换器：
public String  testDelete(@PathVariable("id") String id) ，即可以接受int类型数据id  也可以接受String类型的id


b.可以自定义类型转换器
i.编写 自定义类型转器的类 （实现Converter接口）
public class MyConverter  implements Converter<String,Student>{

	@Override
	public Student convert(String source) {//source:2-zs-23
		//source接受前端传来的String:2-zs-23
		String[] studentStrArr = source.split("-") ;
		Student student = new Student();
		student.setId(  Integer.parseInt(  studentStrArr[0]) );
		student.setName(studentStrArr[1]);
		student.setAge(Integer.parseInt(studentStrArr[2] ));
		return student;
	}

}


ii.配置：将MyConverter加入到springmvc中
<!-- 1将 自定义转换器 纳入SpringIOC容器 -->
	<bean  id="myConverter" class="org.lanqiao.converter.MyConverter"></bean>
	
	<!-- 2将myConverter再纳入 SpringMVC提供的转换器Bean -->
	<bean id="conversionService"  class="org.springframework.context.support.ConversionServiceFactoryBean">
		<property name="converters">
			<set>
				<ref bean="myConverter"/>
			</set>
		</property>
	</bean>
	
	<!-- 3将conversionService注册到annotation-driven中 -->
	<!--此配置是SpringMVC的基础配置，很功能都需要通过该注解来协调  -->
	<mvc:annotation-driven conversion-service="conversionService"></mvc:annotation-driven>

测试转换器：
@RequestMapping(value="testConverter")
public String testConverter(@RequestParam("studentInfo")  Student student) {// 前端：2-zs-23

			System.out.println(student.getId()+","+student.getName()+","+student.getAge());
			
			return "success";
		}



其中@RequestParam("studentInfo")是触发转换器的桥梁：
@RequestParam("studentInfo")接受的数据 是前端传递过来的：2-zs-23  ，但是 需要将该数据 复制给 修饰的目的对象Student；因此SPringMVC可以发现 接收的数据 和目标数据不一致，并且 这两种数据分别是 String、Student,正好符合public Student convert(String source)转换器。


5.数据格式化
SimpleDateForamt sdf = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
SPringMVC提供了很多注解，方便我们数据格式化
实现步骤：
a.配置
<!-- 配置 数据格式化 注解 所依赖的bean -->
<bean id="conversionService" class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
</bean>


b.通过注解使用
@DateTimeFormat(pattern="yyyy-MM-dd")
@NumberFormat(parttern="###,#")  
