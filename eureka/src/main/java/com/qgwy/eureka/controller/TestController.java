package com.qgwy.eureka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {



    @RequestMapping("env")
    @ResponseBody
    public String show(){
        String eurekaHost=System.getenv("EUREKA_HOST");
        return "success"+eurekaHost;
    }
}
