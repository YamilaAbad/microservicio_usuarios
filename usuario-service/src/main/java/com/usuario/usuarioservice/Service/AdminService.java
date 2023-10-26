package com.usuario.usuarioservice.Service;

import com.usuario.usuarioservice.DTO.MonopatinDTO;
import com.usuario.usuarioservice.DTO.ParadaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AdminService {
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
}
