package com.qgwy.eureka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    public String hiService(String name) {
        return restTemplate.getForObject("http://eureka-client/hi?name="+name,String.class);
    }

    public String testHttp(String name) {
        return restTemplate.getForObject("http://192.168.100.56:18836/hi?name="+name,String.class);
    }

}