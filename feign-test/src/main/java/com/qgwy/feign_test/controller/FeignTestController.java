package com.qgwy.feign_test.controller;

import com.qgwy.feign_test.api.FeignApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FeignTestController {
    @Autowired
    private FeignApi feignApi;

    @RequestMapping("/feign/test")
    @ResponseBody
    public String show(String name){
        return feignApi.home(name);
    }
}
