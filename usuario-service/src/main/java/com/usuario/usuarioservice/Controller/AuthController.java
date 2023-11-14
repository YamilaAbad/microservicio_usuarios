package com.usuario.usuarioservice.Controller;

import com.usuario.usuarioservice.JWT.AuthResponse;
import com.usuario.usuarioservice.JWT.RegisterRequest;
import com.usuario.usuarioservice.Service.AuthService;
import com.usuario.usuarioservice.JWT.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> getToken(){
        String token = authService.getToken();
        if (token != null) {
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No se pudo obtener el token.");
        }
    }
}
