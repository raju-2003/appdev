package com.educonnect.raj_narayanan.config;

import static com.educonnect.raj_narayanan.utils.MyConstant.AUTH;
import static com.educonnect.raj_narayanan.utils.MyConstant.HEADERS;
import static com.educonnect.raj_narayanan.utils.MyConstant.METHODS;
import static com.educonnect.raj_narayanan.utils.MyConstant.LOGOUT;
import static com.educonnect.raj_narayanan.utils.MyConstant.ORIGINS;
import static com.educonnect.raj_narayanan.utils.MyConstant.WHITELIST_URL;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JWTauthenticationfilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> corsConfigurationSource())
                .authorizeHttpRequests(
                        authorize -> authorize.requestMatchers(WHITELIST_URL)
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout.logoutUrl(AUTH + LOGOUT)
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler(
                                (request, response, authentication) -> SecurityContextHolder.clearContext()))
                .build();
    }

    @Bean
    //cors -> cross origin resource sharing     
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(ORIGINS);
        corsConfiguration.setAllowedMethods(METHODS);
        corsConfiguration.setAllowedHeaders(HEADERS);
        corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
    
}
