package com.caiza.clinical_alerts.security.config;

import com.caiza.clinical_alerts.security.filter.InternalAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
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
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public InternalAuthFilter internalAuthFilter() {
        return new InternalAuthFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/actuator/**",
                                "/test/**",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        .requestMatchers("/api/telemetry/**").permitAll()

                        .requestMatchers("/api/patients/**", "/api/devices/**").authenticated()

                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}