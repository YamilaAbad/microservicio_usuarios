package com.usuario.usuarioservice.Service;

import com.usuario.usuarioservice.JWT.AuthResponse;
import com.usuario.usuarioservice.JWT.LoginRequest;
import com.usuario.usuarioservice.JWT.RegisterRequest;
import com.usuario.usuarioservice.Model.Rol;
import com.usuario.usuarioservice.Model.Usuario;
import com.usuario.usuarioservice.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    public AuthResponse login(LoginRequest request){
        //autentica al usuario con el gestor de autenticación
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        //obtiene los detalles del usuario desde el repositorio
        UserDetails user= usuarioRepository.findByUsername(request.getUsername()).orElseThrow();
        //genera un token JWT para el usuario autenticado
        String token=jwtService.getToken(user);
        //devuelve la respuesta de autenticación con el token
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request){
        //crea un nuevo usuario con los detalles proporcionados en la solicitud
        Usuario user = Usuario.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword())) //codifica la contraseña antes de guardarla
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .num_de_celular(request.getNum_de_celular())
                .email(request.getEmail())
                .rol(Rol.ADMIN)  //por defecto es un usuario normal
                .build();

        usuarioRepository.save(user);   //salva al usuario
        //genera un token JWT para el usuario registrado y devuelve la respuesta de autenticación
        return AuthResponse.builder()
                .token(jwtService.getToken(user)).build();
    }

    public String getToken() {
        // Obtiene el objeto Authentication del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Verifica si el usuario está autenticado
        if (authentication != null && authentication.isAuthenticated()) {
            // El token estará en las credenciales del objeto Authentication
            Object credentials = authentication.getCredentials();
            if (credentials instanceof String) {
                String token = (String) credentials;
                // Hacer algo con el token (puede ser un JWT u otro tipo de token)
                return token;
            }
        }

        // Manejar el caso en el que el usuario no está autenticado o no se puede obtener el token
        return "No se pudo obtener el token del usuario logueado.";
    }

}
