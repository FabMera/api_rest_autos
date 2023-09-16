package com.project.coches.config;

import com.project.coches.exceptions.AccessDeniedHandlerException;
import com.project.coches.security.JwtAuthFilter;
import com.project.coches.security.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final AccessDeniedHandlerException accessDeniedHandlerException;
    private final JwtAuthFilter jwtAuthFilter;

    //Configuramos las peticiones HTTP

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(withDefaults()).exceptionHandling().accessDeniedHandler(accessDeniedHandlerException).and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(req -> req.requestMatchers("/auth/**", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**")
                        .permitAll().requestMatchers(HttpMethod.GET, "/customer/**").hasAnyRole(Roles.CUSTOMER, Roles.ADMIN)
                        .requestMatchers(HttpMethod.GET, "/cars/**").hasAnyRole(Roles.CUSTOMER, Roles.ADMIN)
                        .requestMatchers(HttpMethod.DELETE, "/customer/**").hasRole(Roles.ADMIN)
                        .requestMatchers(HttpMethod.POST, "cars/**").hasRole(Roles.ADMIN)
                        .requestMatchers(HttpMethod.POST, "customer/**").hasRole(Roles.ADMIN)
                        .anyRequest().authenticated());
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            var cors = new org.springframework.web.cors.CorsConfiguration();
            cors.setAllowCredentials(true);
            cors.addAllowedOriginPattern("*");
            cors.addAllowedHeader("*");
            cors.addAllowedMethod("*");
            return cors;
        };
    }
}
