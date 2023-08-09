/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paymentchain.configserver.segurity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author sotobotero
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Value("${custom.user.name}")
    private String customUserName;

    @Value("${custom.user.password}")
    private String customUserPassword;
    
    @Value("${admin.user.name}")
    private String adminUserName;

    @Value("${admin.user.password}")
    private String adminUserPassword;

    private static final String[] NO_AUTH_LIST = {
        "/v3/api-docs",//
        "/configuration/ui", //
        "/swagger-resources", //
        "/configuration/security", //   
        "/webjars/**",
        "/h2-console/**"};

    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //02- Custom security configuration, we can excluse some paths and ask by user and password before each request to acces swagger ui
        http
                /*- This property is active by default, we need to deactivate in order to allow ask by user and password on post methods.
             
             - Cross site request forgery (CSRF or XSRF) refers to an attack that makes the end-user perform unwanted actions within a web application 
             that has already granted them authentication.
             
            - The best option for protect is use a Token on each request, like  a JWT
                 */
                .csrf().disable()
                //Configure custom restrictions in order to ask by user and password
                .authorizeHttpRequests((authz) -> authz
                .antMatchers(NO_AUTH_LIST).permitAll()
                //.antMatchers(HttpMethod.POST, "/*billing*/**").authenticated()
                //Using defauls values, we can define role on .properties file that will be set whne user is authetnticate
                .antMatchers(HttpMethod.GET, "/*config-client-customer/production*/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/*config-client-customer/development*/**").hasRole("USER")
                )
                //Use default credentials on .properties file
                .httpBasic(withDefaults())
                //use default UI.
                .formLogin(withDefaults());
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser(customUserName)
            .password(passwordEncoder().encode(customUserPassword))
            .roles("USER")
            .and()
            .withUser(adminUserName)
            .password(passwordEncoder().encode(adminUserPassword))
            .roles("ADMIN");
    }

}
