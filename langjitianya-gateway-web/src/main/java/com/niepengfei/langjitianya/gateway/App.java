package com.niepengfei.langjitianya.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author Jack
 */
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class App {

    public static void main(String[] args){
        SpringApplication.run(App.class,args);
    }
}
