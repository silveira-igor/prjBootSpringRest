package controller.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

	    http
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers(
	                "/", 
	                "/index.html",
	                "/dashboard.html",
	                "/favicon.ico",
	                "/auth/**",
	                "/css/**",
	                "/js/**",
	                "/images/**"
	            ).permitAll() // Libera todos as requisições cujo path der 'match' nos itens colocados
	            .anyRequest().authenticated() // para os demais, preciso de autenticação
	        )    
	        .addFilterBefore(new AuthFilter(), 
	                         UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}
}