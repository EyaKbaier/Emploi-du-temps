package com.example.emploi_du_temps.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

        @Bean
        BCryptPasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                                .csrf(csrf -> csrf.disable()) // Désactiver la protection CSRF
                                .cors(cors -> cors.configurationSource(request -> { // Configuration CORS
                                        CorsConfiguration corsConfig = new CorsConfiguration();
                                        corsConfig.setAllowedOrigins(
                                                        Collections.singletonList("http://localhost:4200")); // URL du
                                                                                                             // frontend
                                        corsConfig.setAllowedMethods(Collections.singletonList("*"));
                                        corsConfig.setAllowCredentials(true);
                                        corsConfig.setAllowedHeaders(Collections.singletonList("*"));
                                        corsConfig.setExposedHeaders(Arrays.asList("Authorization"));
                                        corsConfig.setMaxAge(360L);
                                        return corsConfig;
                                }))
                                .authorizeHttpRequests(auth -> auth
                                                // Configuration des autorisations pour chaque type de requête
                                                .requestMatchers(HttpMethod.GET, "/api/getbyid/**")
                                                .hasAnyAuthority("ADMIN", "USER") // Autoriser ADMIN et USER pour GET
                                                .requestMatchers(HttpMethod.POST, "/api/addvoiture/**")
                                                .hasAnyAuthority("ADMIN", "USER") // Autoriser ADMIN et USER pour POST
                                                .requestMatchers(HttpMethod.PUT, "/api/updatevoiture/**")
                                                .hasAuthority("ADMIN") // Autoriser uniquement ADMIN pour PUT
                                                .requestMatchers(HttpMethod.DELETE, "/api/delvoiture/**")
                                                .hasAuthority("ADMIN") // Autoriser uniquement ADMIN pour DELETE
                                                .anyRequest().permitAll() // Autoriser toutes les autres requêtes
                                )
                                .addFilterBefore(new JwtAuthenticationFilter(),
                                                UsernamePasswordAuthenticationFilter.class); // Ajouter le filtre JWT

                return http.build(); // Retourner la configuration finale
        }
}