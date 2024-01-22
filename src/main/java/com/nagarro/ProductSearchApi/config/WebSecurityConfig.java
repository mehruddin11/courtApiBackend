package com.nagarro.ProductSearchApi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nagarro.ProductSearchApi.security.JWTUserDetailsService;
import com.nagarro.ProductSearchApi.security.JwtAuthenticationEntryPoint;
import com.nagarro.ProductSearchApi.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) 
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JWTUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtAuthenticationFilter  jwtAuthenticationFilter;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	 auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests(req -> req
                		.antMatchers("/authenticate", "/register","/generate","/verify",
                				"/createCredentials","/forgot-password-otp","/verify-otp","/reset-password","/feedbacks", "/update-profile/**","/all").permitAll()
                		 .antMatchers("/api/feedbacks/**").permitAll() 
                		 .antMatchers("/api/package/**").permitAll() 
                		 .antMatchers("/api/complain/**").permitAll() 
                		 .antMatchers("/user-selected-packages/**").permitAll() 
                		 .antMatchers("/api/mobilemaintenances/**").permitAll() 
                		 .antMatchers("/api/transactions/**").permitAll() 
                		 .antMatchers("/platform/**").permitAll()
                		 .antMatchers("/api/casemodels/**").permitAll()
                        // .antMatchers("/user").hasRole("USER")
                        // .antMatchers("/admin").hasRole("ADMIN")
                		 .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**").permitAll()
                        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .anyRequest().authenticated()
                		
                	)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(auth-> auth.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                
          .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
