package com.todoTask.crud.repaso.config.security;

import com.todoTask.crud.repaso.config.security.filter.JwtTokenValidator;
import com.todoTask.crud.repaso.services.UserDetailsService;
import com.todoTask.crud.repaso.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtUtil jwtUtils;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    http.requestMatchers(HttpMethod.POST, "/auth/log-in").permitAll()
                            .requestMatchers(HttpMethod.POST, "/auth/sign-up/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.POST,  "/specialities/**").hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.DELETE,  "/specialities/**").hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.PUT,  "/specialities/**").hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.GET,  "/specialities/**").authenticated()
                            //-----------------------------------------SHIFTS ENDPOINTS-------------------------
                                    .requestMatchers(HttpMethod.GET, "/shifts/**").authenticated()
                            .requestMatchers(HttpMethod.POST, "/shifts/**").hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.PUT,  "/shifts/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE,  "/shifts/**").hasRole("ADMIN")
                            //------------------------------patient------------------------------
                                    .requestMatchers(HttpMethod.PUT,  "/patients/**").hasAnyRole("ADMIN", "PATIENT")
                                    .requestMatchers(HttpMethod.GET, "/patients/{id}").hasAnyRole("ADMIN", "PATIENT", "DOCTOR")
                                    .requestMatchers(HttpMethod.DELETE, "/patients/{id}").hasAnyRole("ADMIN")
                                    .requestMatchers(HttpMethod.GET,  "/patients").hasAnyRole("ADMIN")
                            //--------------------------DOCTOR----------------------------------------
                                    .requestMatchers(HttpMethod.PUT,  "/doctors/{id}").hasAnyRole("ADMIN", "DOCTOR")
                                    .requestMatchers(HttpMethod.DELETE,  "/doctors/{id}").hasAnyRole("ADMIN")
                                    .requestMatchers(HttpMethod.GET,  "/doctors/**").hasAnyRole("ADMIN", "DOCTOR, PATIENT")
                            .requestMatchers(HttpMethod.GET,  "/patients").hasAnyRole("ADMIN", "DOCTOR, PATIENT")
                                    //-------------------------DATE--------------------------------------------------------
                                            .requestMatchers(HttpMethod.PUT,  "/dates/**").authenticated()
                                    .requestMatchers(HttpMethod.DELETE,  "/dates/**").hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.GET,  "/dates/**").authenticated()
                                    .requestMatchers(HttpMethod.PATCH,  "/dates/**").hasRole("DOCTOR");
                    http.anyRequest().permitAll();
                })
                //CURTOM FILTER IMPL
                .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
