package com.bilolbek.ConsultingWebsite.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bilolbek.ConsultingWebsite.components.JWTFilter;
import com.bilolbek.ConsultingWebsite.services.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JWTFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
        .csrf(customize -> customize.disable())
        .cors(Customizer.withDefaults())
        .authorizeHttpRequests((requests) -> requests
                                    .requestMatchers("/api/v1/auth/**").permitAll()
                                    .requestMatchers("/api/v1/get/**").permitAll()
                                    .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                                    .requestMatchers("/api/v1/request/**").authenticated()
                                    .requestMatchers("/api/v1/getProtected/**").authenticated()
                                    .anyRequest().permitAll()
        )
        .httpBasic(Customizer.withDefaults())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));

        provider.setUserDetailsService(myUserDetailsService);

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry corsRegistry){
                corsRegistry.addMapping("/api/v1/**")
                .allowedOrigins("http://localhost:5173", "https://consultingozoda.netlify.app")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }

}
