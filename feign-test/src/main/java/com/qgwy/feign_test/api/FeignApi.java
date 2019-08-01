package com.qgwy.feign_test.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("eureka-client")
public interface FeignApi {

    @RequestMapping("/hi")
    public String home(@RequestParam(value = "name") String name);
}
