package com.caiza.clinical_alerts.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@EnableWebSecurity is not strictly necessary in Spring Boot applications,
// as it is included by default when spring-boot-starter-security is on the classpath.
// However, it can be used to explicitly enable web security and customize the configuration if needed.
// In this case, since we are defining a SecurityFilterChain bean,
// it is good practice to include @EnableWebSecurity to indicate that we are configuring web security for our application.
//Enables Spring Security
//Allows configuration of authentication and authorization
//Register the application´s security filter
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain
            (HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers("/test/**").permitAll()
                        .requestMatchers("/api/patients/**").permitAll()
                        .requestMatchers("/api/telemetry/**").permitAll()
                        .requestMatchers(("/api/devices/**")).permitAll()
                        .requestMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/v3/api-docs.yaml"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}