package com.waterinc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Created by Asus on 5/10/2017.
 */
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    SecurityUserDetailService securityUserDetailService;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityUserDetailService);
//        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("SELECT username, password, enabled from user where username=?")
//                .authoritiesByUsernameQuery("select username, role from user where username=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/login.jsp").permitAll()
//                .antMatchers("login.jsp").permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated().and().formLogin().loginPage("/login.jsp").loginProcessingUrl("/login").defaultSuccessUrl("/index.jsp", true).failureUrl("/login.jsp?error=true").and().csrf().disable();
//        http.authorizeRequests().anyRequest().permitAll().and().csrf().disable();
    }

}
