package com.firstapp.app1.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {
    // LDAP OR DATABASE
    // InMemoryUserDetailsManager
    // InMemoryUserDetailsManager();
    

    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager(){
        Function<String, String> password=
            input -> passwordEncoder().encode(input);
        UserDetails userDetails =User.builder()
                                                .passwordEncoder(password)
                                                .username("user1")
                                                .password("pass1")
                                                .roles("USER","ADMIN")
                                                .build();

        return new InMemoryUserDetailsManager(userDetails);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(
				auth -> auth.anyRequest().authenticated());
		http.formLogin(withDefaults());

		http.csrf(csrf -> csrf.disable());
		http.headers(headers -> headers.frameOptions(frameOptionsConfig-> frameOptionsConfig.disable()));
		
		return http.build();
	}
}
