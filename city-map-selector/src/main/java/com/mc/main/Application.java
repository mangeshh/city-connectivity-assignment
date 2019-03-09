package com.mc.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableFeignClients("com.mc.resource")
@EnableDiscoveryClient
@ComponentScan({"com.mc.resource", "com.mc.main" })
public class Application {
    public static void main(String[] args) {
       SpringApplication.run(Application.class, args);
    }
}

