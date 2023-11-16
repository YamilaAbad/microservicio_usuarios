package com.usuario.usuarioservice.Controller;

import com.usuario.usuarioservice.Service.AuthService;
import com.usuario.usuarioservice.Service.JwtService;
import io.netty.handler.codec.http.HttpMethod;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
@RestController
@RequestMapping("/microservicio_usuario")
public class SecurityController {
    private final WebClient webClientMonopatin;
    private final WebClient webClientViaje;
    private final JwtService jwtService;
    private final AuthService authService;

    public SecurityController(WebClient.Builder webClientBuilder, JwtService jwtService, AuthService authService) {
        this.webClientMonopatin = webClientBuilder.baseUrl("http://localhost:8089").build();
        this.webClientViaje = webClientBuilder.baseUrl("http://localhost:8080/service_viaje/").build();
        this.jwtService = jwtService;
        this.authService = authService;
    }

    /**************************** MONOPATIN **********************************************/
    @GetMapping("/some-enpoint-monopatin")
    public ResponseEntity<String> getSomeResourceGetMonopatin(String uri){
        return getSomeResourceMonopatin(uri, HttpMethod.GET, null);
    }
    @PostMapping("/some-enpoint-monopatin")
    public ResponseEntity<String>getSomeResourcePostMonopatin(@RequestParam String uri, @RequestBody(required = false) String requestBody){
        return getSomeResourceMonopatin(uri, HttpMethod.POST, requestBody);
    }
    @PutMapping("/some-enpoint-monopatin")
    public ResponseEntity<String>getSomeResourcePutMonopatin(@RequestParam String uri, @RequestBody(required = false) String requestBody){
        return getSomeResourceMonopatin(uri, HttpMethod.PUT, requestBody);
    }
    @DeleteMapping("/some-enpoint-monopatin")
    public ResponseEntity<String>getSomeResourceDeleteMonopatin(@RequestParam String uri){
        return getSomeResourceMonopatin(uri, HttpMethod.DELETE, null);
    }
    private ResponseEntity<String> getSomeResourceMonopatin(String uri, HttpMethod method, String requestBody){
        //obtener el token para el usuario actual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Verificar si el usuario está autenticado
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autenticado");
        }

        // Obtener el UserDetails del objeto Authentication
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Generar el token JWT para el usuario actual
        String token = jwtService.getToken(userDetails);
        // Configurar el encabezado con el token
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        if(method.equals(HttpMethod.POST)){
            headers.setContentType(MediaType.APPLICATION_JSON);
        }
        try{
            WebClient.RequestHeadersSpec<?> request;
            if(method.equals(HttpMethod.GET)){
                request = webClientMonopatin.get()
                        .uri(uri)
                        .headers(h->h.setBearerAuth(token));
            }else {
                request = webClientMonopatin.post()
                        .uri(uri)
                        .headers(h->h.setBearerAuth(token))
                        .contentType(MediaType.APPLICATION_JSON) // Establecer explícitamente el tipo de contenido a 'application/json'
                        .bodyValue(requestBody);
            }
            //realizao la solicitud al microservicio de monopatin
            String response = request.retrieve()
                    .bodyToMono(String.class)
                    .block();

            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al hacer la solicitud: " + e.getMessage());
        }
    }


    /**************************** VIAJE **********************************************/


    @GetMapping("/some-enpoint-vijae")
    public ResponseEntity<String> getSomeResourceGetViaje(String uri){
        return getSomeResourceViaje(uri, HttpMethod.GET, null);
    }
    @PostMapping("/some-enpoint-viaje")
    public ResponseEntity<String>getSomeResourcePostViaje(@RequestParam String uri, @RequestBody(required = false) String requestBody){
        return getSomeResourceViaje(uri, HttpMethod.POST, requestBody);
    }
    @PutMapping("/some-enpoint-viaje")
    public ResponseEntity<String>getSomeResourcePutViaje(@RequestParam String uri, @RequestBody(required = false) String requestBody){
        return getSomeResourceViaje(uri, HttpMethod.PUT, requestBody);
    }

    private ResponseEntity<String> getSomeResourceViaje(String uri, HttpMethod method, String requestBody){
        //obtener el token para el usuario actual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Verificar si el usuario está autenticado
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autenticado");
        }

        // Obtener el UserDetails del objeto Authentication
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Generar el token JWT para el usuario actual
        String token = jwtService.getToken(userDetails);
        // Configurar el encabezado con el token
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        if(method.equals(HttpMethod.POST)){
            headers.setContentType(MediaType.APPLICATION_JSON);
        }
        try{
            WebClient.RequestHeadersSpec<?> request;
            if(method.equals(HttpMethod.GET)){
                request = webClientViaje.get()
                        .uri(uri)
                        .headers(h->h.setBearerAuth(token));
            }else {
                request = webClientViaje.post()
                        .uri(uri)
                        .headers(h->h.setBearerAuth(token))
                        .contentType(MediaType.APPLICATION_JSON) // Establecer explícitamente el tipo de contenido a 'application/json'
                        .bodyValue(requestBody);
            }
            //realizao la solicitud al microservicio de monopatin
            String response = request.retrieve()
                    .bodyToMono(String.class)
                    .block();

            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al hacer la solicitud: " + e.getMessage());
        }
    }

}
