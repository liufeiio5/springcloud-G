package com.qgwy.config_client.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RefreshScope
public class ClientController {

    @Value("${clientParam:bbb}")
    private String initVal;
    @Value("${project:vvvv}")
    private String project;


    @GetMapping("/show")
    @ResponseBody
    public String show(){
        return "success" +"==="+initVal+"===="+project;
    }
}
