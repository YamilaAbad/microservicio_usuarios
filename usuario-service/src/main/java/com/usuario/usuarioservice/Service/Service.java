package com.usuario.usuarioservice.Service;
import com.usuario.usuarioservice.Controller.SecurityController;
import com.usuario.usuarioservice.DTO.MonopatinDTO;
import com.usuario.usuarioservice.DTO.ParadaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    @Value("${url_monopatin}")
    private String url_monopatin;
    @Value("${url_viaje}")
    private String url_viaje;

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    AuthService authService;
    @Autowired
    SecurityController securityController;


    public void agregarMonopatin(MonopatinDTO monopatin) {
         this.restTemplate.postForObject(this.url_monopatin+"/monopatin/crearMonopatin", monopatin, MonopatinDTO.class);
    }

    public void agregarParada(ParadaDTO parada){

        this.restTemplate.postForObject(this.url_monopatin+"/parada/crearParada", parada, ParadaDTO.class);
    }

    public ResponseEntity<String> consultaMonopatines() {
        String uri = "/monopatin/reporteMonopatinesEstado";
        ResponseEntity<String> responseEntity = securityController.getSomeResource(uri);
        return responseEntity;
    }

    public ResponseEntity<List<MonopatinDTO>> monopatinesCercanos(String ubicacion) {
        ResponseEntity<List<MonopatinDTO>> responseEntity = this.restTemplate.exchange(
                this.url_monopatin + "/monopatin/monopatinesCercanos/" + ubicacion,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MonopatinDTO>>() {}
        );

        return responseEntity;
    }

    public ResponseEntity<String> modificarTarifaEnFecha(LocalDate fecha, String id, String valor) {
        LocalDate fechaActual = LocalDate.now();
        if (fechaActual.isEqual(fecha)) {
            ResponseEntity<String> response = this.restTemplate.exchange(
                    this.url_viaje + "/actualizarTarifa/" + id + "/" + valor,
                    HttpMethod.PUT,
                    null,
                    String.class
            );
            return response;
        }

        return ResponseEntity.badRequest().body("La fecha no coincide para modificar la tarifa.");
    }

    public ResponseEntity<List<MonopatinDTO>> monopatinesConCantViajesEnAnio(){
        ResponseEntity<List<MonopatinDTO>> responseEntity = this.restTemplate.exchange(
                this.url_monopatin + "/monopatin/cantidadDeViajePorAnio/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MonopatinDTO>>() {}
        );

        return responseEntity;
    }

}
