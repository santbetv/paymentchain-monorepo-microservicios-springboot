package com.paymentchain.adminserver;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableAutoConfiguration
@EnableAdminServer
@EnableEurekaClient
public class AdminserverApplication{

    public static void main(String[] args) {
        SpringApplication.run(AdminserverApplication.class, args);
    }
}

//configuracion basica para admin server y actuator

//public class AdminserverApplication extends WebSecurityConfigurerAdapter {
//    public static void main(String[] args) {
//        SpringApplication.run(AdminserverApplication.class, args);
//    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests().anyRequest().permitAll();
//    }
//}
