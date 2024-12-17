package com.openclassrooms.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(customizer -> customizer.disable())
      .authorizeHttpRequests(request -> request
        .anyRequest().permitAll())
      .httpBasic(Customizer.withDefaults());
    return http.build();
  }

  @Bean
  public UserDetailsService userDetailsManager() {

    UserDetails emma = User.builder()
      .username("Emma")
      .password("{noop}test123")
      .build();

    return new InMemoryUserDetailsManager(emma);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    var encoders = new HashMap<String, PasswordEncoder>(
      Map.of("bcrypt",new BCryptPasswordEncoder(),
        "noop", NoOpPasswordEncoder.getInstance())
    );

    var e = new DelegatingPasswordEncoder("noop", encoders);
    return e;
  }
}
