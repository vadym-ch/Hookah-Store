package com.kpi.internetshop.config;

import com.kpi.internetshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers( "/registration**").permitAll()
                .antMatchers(HttpMethod.GET, "/products").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/products/delete/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/products/update/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/save").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/products/add").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/users/delete/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/orders").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/orders/details/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/orders/delete/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/cart/show").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/cart/clean").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/cart/delete/{id}").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/cart/add/{id}").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/cart/update/{id}").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/cart/save").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/order/complete").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/orders/show").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/user/orders/details/{id}").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied");
    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(getEncoder());
        return auth;
    }
}
