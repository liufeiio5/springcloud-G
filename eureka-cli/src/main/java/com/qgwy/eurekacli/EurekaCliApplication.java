package com.qgwy.eurekacli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EurekaCliApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaCliApplication.class, args);
    }

}
