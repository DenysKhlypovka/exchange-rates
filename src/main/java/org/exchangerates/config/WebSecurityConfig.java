package org.exchangerates.config;

import org.exchangerates.JwtAuthenticationEntryPoint;
import org.exchangerates.JwtRequestFilter;
import org.exchangerates.service.EncodingService;
import org.exchangerates.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
  @Autowired
  private UserService userService;
  @Autowired
  private JwtRequestFilter jwtRequestFilter;
  @Autowired
  private EncodingService encodingService;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService).passwordEncoder(encodingService.getPasswordEncoder());
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf().disable().authorizeRequests()
        .antMatchers("/home").permitAll()
        .antMatchers("/user/**").permitAll()
        .antMatchers("/bank-data/**").permitAll()
        .antMatchers("/authenticate").permitAll().anyRequest().authenticated()
        .and().exceptionHandling()
        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
        .and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
  }
}