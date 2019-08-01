package com.qgwy.feign_test.api.fallback;

import com.qgwy.feign_test.api.FeignApi;
import org.springframework.stereotype.Component;

@Component
public class FeignFallback implements FeignApi {
    @Override
    public String home(String name) {
        return "服务暂时不可用，请稍后再试";
    }
}
