package org.legend.handler;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//接口/类型 注解 配置
/*
    org.xxx.servlet
    org.xxx.controller
    org.xxx.handler
    org.xxx.action
都是指后台(Servlet组件环节)controller层
 */

/**
 * 控制器: 建立与springmvc的映射关系(bean、@RequestMapping拦截请求...)
 */
@Controller
public class SpringMvcHandler {
    @RequestMapping("welcome")
    public String welcome(){
        return "success";
    }
}
