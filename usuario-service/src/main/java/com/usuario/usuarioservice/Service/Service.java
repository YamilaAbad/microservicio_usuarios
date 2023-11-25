package com.usuario.usuarioservice.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usuario.usuarioservice.Controller.SecurityController;
import com.usuario.usuarioservice.DTO.MonopatinDTO;
import com.usuario.usuarioservice.DTO.ParadaDTO;
import com.usuario.usuarioservice.DTO.PausaDTO;
import com.usuario.usuarioservice.DTO.ViajeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;


import java.time.LocalDate;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    SecurityController securityController;

    //!----------------------------------------- Acciones del Administrador---------------------------------------------
    public void agregarMonopatin(MonopatinDTO monopatin) {
        String uri = "/monopatin/crearMonopatin";
        ObjectMapper mapper = new ObjectMapper();
        String requestBody = "";
        try {
            requestBody = mapper.writeValueAsString(monopatin); // esto es para convertir un MonopatinDTO a un JSON válido
        } catch (Exception e) {
            e.printStackTrace();
        }
        ResponseEntity<String> responseEntity = securityController.getSomeResourcePostMonopatin(uri, requestBody);
    }
    public void agregarParada(ParadaDTO parada){
        String uri = "/parada/crearParada";
        ObjectMapper mapper = new ObjectMapper();
        String requestBody = "";
        try {
            requestBody = mapper.writeValueAsString(parada); // convierte una ParadaDTO en un JSON válido
        } catch (Exception e) {
            e.printStackTrace();
        }
        ResponseEntity<String> responseEntity = securityController.getSomeResourcePostMonopatin(uri, requestBody);
    }

    public ResponseEntity<String> consultaMonopatines() {
        String uri = "/monopatin/reporteMonopatinesEstado";
        ResponseEntity<String> responseEntity = securityController.getSomeResourceGetMonopatin(uri);
        return responseEntity;
    }
    public ResponseEntity<String> listaMonopatines() {
        String uri = "/monopatin/monopatines";
        ResponseEntity<String> responseEntity = securityController.getSomeResourceGetMonopatin(uri);
        return responseEntity;
    }
    public ResponseEntity<String> infoMonopatin(String id) {
        String uri = "/monopatin/monopatin/"+id;
        ResponseEntity<String> responseEntity = securityController.getSomeResourceGetMonopatin(uri);
        return responseEntity;
    }
    public ResponseEntity<String> modificarTarifaEnFecha(LocalDate fecha, String id, String valor) {
        LocalDate fechaActual = LocalDate.now();
        if (fechaActual.isEqual(fecha)) {
            String uri = "/actualizarTarifa/" + id + "/" + valor;
            ResponseEntity<String> response = securityController.getSomeResourceGetViaje(uri);
            return response;
        }
        return ResponseEntity.badRequest().body("La fecha no coincide para modificar la tarifa.");
    }
    public void eliminarMonopatin(String id) {
        String uri = "/monopatin/eliminar/"+id;
        ResponseEntity responseEntity = securityController.getSomeResourceDeleteMonopatin(uri);
    }
    public void actualizarParada(String id, ParadaDTO paradaDTO) {
        String uri = "/parada/actualizarParada/"+id;
        ObjectMapper mapper = new ObjectMapper();
        String requestBody = "";
        try {
            requestBody = mapper.writeValueAsString(paradaDTO); // convierte una ParadaDTO en un JSON válido
        } catch (Exception e) {
            e.printStackTrace();
        }
        securityController.getSomeResourcePutMonopatin(uri,requestBody);
    }
    public void agregarMonopatinAparada(String ubicacion, MonopatinDTO monopatin) {
        String uri = "/parada/agregarMonopatinAParada/"+ubicacion;
        ObjectMapper mapper = new ObjectMapper();
        String requestBody = "";
        try {
            requestBody = mapper.writeValueAsString(monopatin); // convierte una ParadaDTO en un JSON válido
        } catch (Exception e) {
            e.printStackTrace();
        }
        securityController.getSomeResourcePutMonopatin(uri,requestBody);
    }
    public String eliminarParada(String id) {
        String uri = "/parada/eliminarParada/"+id;
        ResponseEntity<String> response = securityController.getSomeResourceDeleteMonopatin(uri);
        return response.getBody();
    }
    public ResponseEntity<String> obtenerMonopatinConMasViajesEnAnio(int x, int anio) {
        String uri="/monopatin/monopatinesConMasDeXViajesEnAnio/"+x+"/"+"/"+anio;
        ResponseEntity<String>response = securityController.getSomeResourceGetMonopatin(uri);
        return response;
    }
    public ResponseEntity<String> facturacionFiltro(int anio, int mesInicio, int mesFin) {
        String uri="/facturacion/"+"/"+anio+"/"+mesInicio+"/"+mesFin;
        ResponseEntity<String> response = securityController.getSomeResourceGetViaje(uri);
        return response;
    }

    //!------------------------------- Acciones del encargado de mantenimiento -----------------------------------------
    public ResponseEntity<String> reportePorKM(int km) {
        String uri = "/monopatin/reporteMonopatinesPorKmsRecorridos/"+km;
        ResponseEntity<String> responseEntity = securityController.getSomeResourceGetMonopatin(uri);
        return responseEntity;
    }

    public ResponseEntity<String> obtenerReporteMonopatinesPorKm( int kmParaMantenimiento) {
        String uri = "/monopatin/reportePorKilometro/" + kmParaMantenimiento;
        ResponseEntity<String> response = securityController.getSomeResourceGetMonopatin(uri);
        return response;
    }

    //!------------------------------------------ Acciones del usuario -------------------------------------------------
    public ResponseEntity<String> monopatinesCercanos(String ubicacion) {
        String uri= "/monopatin/monopatinesCercanos/"+ubicacion;
        ResponseEntity<String> responseEntity = securityController.getSomeResourceGetMonopatin(uri);
        return responseEntity;
    }
    public ResponseEntity<String> listaParadas() {
        String uri = "/parada/paradas";
        ResponseEntity<String> responseEntity = securityController.getSomeResourceGetMonopatin(uri);
        return responseEntity;
    }

    public ResponseEntity<String> infoParada(String id) {
        String uri = "/parada/paradaID/"+id;
        ResponseEntity<String> responseEntity = securityController.getSomeResourceGetMonopatin(uri);
        return responseEntity;
    }

    public ResponseEntity<String> monopatinEnUbicacionParada(String ubicacion) {
        String uri ="/parada/monopatinesEnParada/"+ubicacion;
        ResponseEntity<String> responseEntity = securityController.getSomeResourceGetMonopatin(uri);
        return responseEntity;
    }

    public ResponseEntity<String> paradasEnUbicacion(String ubicacion) {
        String uri = "/parada/paradaEnUbicacion/"+ubicacion;
        ResponseEntity<String> responseEntity = securityController.getSomeResourceGetMonopatin(uri);
        return responseEntity;
    }
    public void iniciarViaje(String id, ViajeDTO viajeDTO) {
        String uri="/monopatin/iniciarViaje/"+id;
        ObjectMapper mapper = new ObjectMapper();
        String requestBody = "";
        try {
            requestBody = mapper.writeValueAsString(viajeDTO); // Aquí convertimos viajeDTO a JSON
        } catch (Exception e) {
            e.printStackTrace();
        }
        ResponseEntity<String>responseEntity = securityController.getSomeResourcePostMonopatin(uri, requestBody) ;
        if (responseEntity.getStatusCode() != HttpStatus.OK) { // Aquí manejamos la respuesta
            throw new RuntimeException("Error al iniciar el viaje: " + responseEntity.getStatusCode());
        }
    }
    public void finalizarViaje(String id, ViajeDTO viajeDTO) {
        String uri="/monopatin/finalizarViaje/"+id;
        ObjectMapper mapper = new ObjectMapper();
        String requestBody = "";
        try {
            requestBody = mapper.writeValueAsString(viajeDTO); // Aquí convertimos viajeDTO a JSON

        } catch (Exception e) {
            e.printStackTrace();
        }
        ResponseEntity<String>responseEntity = securityController.getSomeResourcePutMonopatin(uri, requestBody) ;
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            // Agregar información adicional al mensaje de error
            throw new RuntimeException("Error al finalizar el viaje. Status: " + responseEntity.getStatusCode() + ", Body: " + responseEntity.getBody());
        }
    }
    public void pausarViaje(String id, PausaDTO pausaDTO) {
        String uri="/monopatin/pausaViaje/"+id;
        ObjectMapper mapper = new ObjectMapper();
        String requestBody = "";
        try {
            requestBody = mapper.writeValueAsString(pausaDTO); // Aquí convertimos viajeDTO a JSON
        } catch (Exception e) {
            e.printStackTrace();
        }
        ResponseEntity<String>responseEntity = securityController.getSomeResourcePostMonopatin(uri, requestBody) ;
        if (responseEntity.getStatusCode() != HttpStatus.OK) { // Aquí manejamos la respuesta
            throw new RuntimeException("Error al iniciar el viaje: " + responseEntity.getStatusCode());
        }
    }
    public void cancelarPausa(String id, PausaDTO pausaDTO) {
        String uri="/monopatin/cancelarpausa/"+id;
        ObjectMapper mapper = new ObjectMapper();
        String requestBody = "";
        try {
            requestBody = mapper.writeValueAsString(pausaDTO); // Aquí convertimos viajeDTO a JSON

        } catch (Exception e) {
            e.printStackTrace();
        }
        ResponseEntity<String>responseEntity = securityController.getSomeResourcePutMonopatin(uri, requestBody) ;
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            // Agregar información adicional al mensaje de error
            throw new RuntimeException("Error al finalizar el viaje. Status: " + responseEntity.getStatusCode() + ", Body: " + responseEntity.getBody());
        }
    }









    public ResponseEntity<String> monopatinesConCantViajesEnAnio(){
        String uri = "/monopatin/cantidadDeViajePorAnio/";
        ResponseEntity<String> responseEntity = securityController.getSomeResourceGetMonopatin(uri);
        return responseEntity;
    }











    public String estadoDeLaParada(String ubicacion) {
        String uri = "/parada/estadoDeLaParada/"+ubicacion;
        ResponseEntity<String> response = securityController.getSomeResourceGetMonopatin(uri);
        return response.getBody();
    }
















}
