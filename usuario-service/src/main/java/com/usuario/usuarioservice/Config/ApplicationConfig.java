package com.usuario.usuarioservice.Config;

import com.usuario.usuarioservice.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final UsuarioRepository usuarioRepository;
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager(); //debuelve una autenticación configurada
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(); //proveedor de autenticación por defecto
        authenticationProvider.setUserDetailsService(userDetailService()); //establece el servicio de detalles de usuario
        authenticationProvider.setPasswordEncoder(passwordEncoder()); //establece el codificador de contraseñas
        return authenticationProvider; //devuelve el proveedor de autenticación configurado

    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); //devuelve un codificador de contraseñas bcrypt
    }
    @Bean
    public UserDetailsService userDetailService() {
        return username -> usuarioRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found")); //busca un usuario por su nombre de usuario y lanza una excepción si no se encuentra
    }
}
