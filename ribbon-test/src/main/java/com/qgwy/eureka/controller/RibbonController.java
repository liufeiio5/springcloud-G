package com.qgwy.eureka.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.qgwy.eureka.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RibbonController {

    @Autowired
    HelloService helloService;

    @RequestMapping(value = "/hi")
    @ResponseBody
    @HystrixCommand(fallbackMethod = "errorFallback")
    public String hi(@RequestParam String name) {
        return helloService.hiService(name);
    }

    @RequestMapping(value = "/hi/http")
    @ResponseBody
    public String hiHttp(@RequestParam String name) {
        return helloService.testHttp(name);
    }


    public String errorFallback(@RequestParam String name){
        return "暂时无法访问，请稍后再试";
    }
}
