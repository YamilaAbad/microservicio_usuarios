package com.usuario.usuarioservice.Service;

import com.usuario.usuarioservice.DTO.CuentaDTO;
import com.usuario.usuarioservice.DTO.MonopatinDTO;
import com.usuario.usuarioservice.DTO.ParadaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    @Value("${url_monopatin}")
    private String url_monopatin;

    @Autowired
    RestTemplate restTemplate;

    public void agregarMonopatin(MonopatinDTO monopatin) {
         this.restTemplate.postForObject(this.url_monopatin+"/monopatin/crearMonopatin", monopatin, MonopatinDTO.class);
    }

    public void agregarParada(ParadaDTO parada){
        this.restTemplate.postForObject(this.url_monopatin+"/parada/crearParada", parada, ParadaDTO.class);
    }

    public ResponseEntity<String> consultaMonopatines(){
       return this.restTemplate.getForEntity(this.url_monopatin+"/monopatin/reporteMonopatinesEstado", String.class);
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
}
