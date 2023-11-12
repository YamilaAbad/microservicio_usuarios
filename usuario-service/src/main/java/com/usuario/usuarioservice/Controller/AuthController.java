package com.usuario.usuarioservice.Controller;

import com.usuario.usuarioservice.DTO.UsuarioDTO;
import com.usuario.usuarioservice.JWT.AuthResponse;
import com.usuario.usuarioservice.JWT.RegisterRequest;
import com.usuario.usuarioservice.Service.AuthService;
import com.usuario.usuarioservice.JWT.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/microservicio_usuario/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }

    @GetMapping("/getToken")
    public String getToken(){
        return authService.getToken();
    }
}
