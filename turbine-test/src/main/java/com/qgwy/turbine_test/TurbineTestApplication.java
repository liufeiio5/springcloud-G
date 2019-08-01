package com.qgwy.turbine_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableTurbine
@EnableEurekaClient
public class TurbineTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TurbineTestApplication.class, args);
    }

}
