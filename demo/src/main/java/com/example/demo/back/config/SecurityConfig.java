package com.example.demo.back.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.example.demo.back.infra.security.SecurityFilter;
import com.example.demo.back.service.CombinedUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    SecurityFilter securityFilter;

    @Autowired
    private CombinedUserDetailsService combinedUserDetailsService;

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(combinedUserDetailsService)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Libera OPTIONS para CORS
                        .requestMatchers(HttpMethod.OPTIONS, "/servico/**").permitAll() // Libera OPTIONS para /servico
                        .requestMatchers(HttpMethod.OPTIONS, "/obra/**").permitAll() // Libera OPTIONS para /obra
                        .requestMatchers(HttpMethod.GET, "/gestaoDeObra/**").permitAll() // Permite GET para /obra/fornecedor/{id}
                        .requestMatchers(HttpMethod.POST, "/gestaoDeObra").authenticated()
                        .requestMatchers(HttpMethod.GET, "/pagamento").authenticated()
                        .requestMatchers(HttpMethod.GET, "/pagamento").authenticated().requestMatchers(HttpMethod.GET, "/pagamento").authenticated()
                        .requestMatchers(HttpMethod.POST, "/pagamento").authenticated()
                        .requestMatchers(HttpMethod.GET, "/pagamento/{id}").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/pagamento/{id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/pagamento/delete/{id}").authenticated()
                        .requestMatchers(HttpMethod.GET, "/obra/forncedor/indicador/{id}").authenticated()
                        .requestMatchers(HttpMethod.GET, "/obra").authenticated()
                        .requestMatchers(HttpMethod.POST, "/obra").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/obra/{id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/obra/delete/{id}").authenticated()
                        .requestMatchers(HttpMethod.GET, "/servico").authenticated()
                        .requestMatchers(HttpMethod.POST, "/servico").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/servico/{id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/servico/delete/{id}").authenticated()
                        .requestMatchers(HttpMethod.GET, "/cliente/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/cliente/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/cliente/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/cliente/tipoUser").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/cliente/{id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/cliente/delete/{id}").authenticated()
                        .requestMatchers(HttpMethod.GET, "/fornecedor/{id}").permitAll()  // Permite GET para /fornecedor/{id}
                        .requestMatchers(HttpMethod.POST, "/fornecedor/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/fornecedor/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/fornecedor/tipoUser").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/fornecedor/totalObras/{id}").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/fornecedor/totalObrasNoPrazo/{id}").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/fornecedor/{id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/fornecedor/delete/{id}").authenticated()
                        .anyRequest().authenticated() // Qualquer outra requisição precisa estar autenticada
                )
                .cors(cors -> cors.configurationSource(request -> {
                    var corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedOrigins(List.of("http://localhost:63342/", "http://127.0.0.1:5500",
                            "http://localhost:5500", "http://127.0.0.1:5501", "http://127.0.0.1:5502"));
                    corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    corsConfiguration.setAllowedHeaders(List.of("*"));
                    corsConfiguration.setAllowCredentials(true);
                    return corsConfiguration;
                }))
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
