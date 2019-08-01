package com.qgwy.feign_test.api;

import com.qgwy.feign_test.api.fallback.FeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "eureka-client",fallback = FeignFallback.class)
public interface FeignApi {

    @RequestMapping("/hi")
    public String home(@RequestParam(value = "name") String name);
}
