package com.hongseng.app.config;

import com.hongseng.app.config.jwtfilter.JWTAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @program: spring-security
 * @description:
 * @author: fbl
 * @create: 2020-12-01 14:40
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 禁用 CSRF
                .csrf().disable()

                // 防止iframe 造成跨域
                .headers()
                .frameOptions()
                .disable()

                // 不创建会话
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        http
                .authorizeRequests() // 授权配置
                //无需权限访问
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated() // anyRequest 只能配置一个，多个会报错
                //其他接口需要登录后才能访问
                .and()
                .addFilter(new JWTAuthorizationFilter(authenticationManager()));

    }


    /**
     * 自定义用户名和密码有2种方式，一种是在代码中写死 另一种是使用数据库
     */

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
