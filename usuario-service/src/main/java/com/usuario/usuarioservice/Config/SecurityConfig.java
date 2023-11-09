package com.usuario.usuarioservice.Config;

import com.usuario.usuarioservice.JWT.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity //habilita la seguridad web en la aplicación
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())  //deshabilita la protección CSRF (Cross-Site Request Forgery)
                .authorizeHttpRequests(authRequest ->
                authRequest
                        .requestMatchers("/microservicio_usuario/auth/**").permitAll() //permite el acceso a rutas específicas sin autenticación (solo login y registro)
                        .anyRequest().authenticated() //requiere autenticación para cualquier otra solicitud
                        )
                .sessionManagement(sessionManager ->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //configura la política de creación de sesión como sin estado (stateless)
                .authenticationProvider(authenticationProvider) //configura el proveedor de autenticación
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) //agrega el filtro JWT antes del filtro de nombre de usuario y contraseña
                .build(); //construye y devuelve la cadena de filtros de seguridad
    }
}
