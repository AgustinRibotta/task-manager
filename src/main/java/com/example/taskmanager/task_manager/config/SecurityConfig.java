package com.example.taskmanager.task_manager.config;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.SecurityFilterChain;

import com.example.taskmanager.task_manager.services.imp.UserDetailsServiceImpl;

import javax.crypto.spec.SecretKeySpec;
import java.util.Collections;
import java.util.stream.Collectors;

@Configuration
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Value("${jwt.secret}")
    private String secretKey;

    // Defines the main security filter chain that configures HTTP security
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable) 
            .authorizeHttpRequests(auth -> auth
                // Public endpoints accessible without authentication
                .requestMatchers("/auth/login").permitAll()
                .anyRequest().authenticated()
            )
            // Configure OAuth2 Resource Server to use JWT tokens
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt
                    .decoder(jwtDecoder()) 
                    .jwtAuthenticationConverter(jwtAuthenticationConverter()) 
                )
            );

        return http.build();
    }

    // Bean to decode JWT tokens using a symmetric secret key (HS256 algorithm)
    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withSecretKey(
            new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS256.getJcaName())
        ).build();
    }

    // Converts JWT claims (especially "roles") into Spring Security GrantedAuthority objects
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            var roles = jwt.getClaimAsStringList("roles");
            if (roles == null) {
                roles = Collections.emptyList();
            }

            return roles.stream()
                .map(role -> role.startsWith("ROLE_") ? role : "ROLE_" + role) // Ensure roles have 'ROLE_' prefix
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        });

        return converter;
    }

    // Exposes the AuthenticationManager bean used for authenticating user credentials
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    // Configures a DaoAuthenticationProvider with a UserDetailsService and PasswordEncoder
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsServiceImpl);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    // Password encoder bean using BCrypt hashing algorithm
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}