package com.usuario.usuarioservice.Controller;
import com.usuario.usuarioservice.DTO.MonopatinDTO;
import com.usuario.usuarioservice.DTO.ParadaDTO;
import com.usuario.usuarioservice.Model.Cuenta;
import com.usuario.usuarioservice.Model.Usuario;
import com.usuario.usuarioservice.Repository.CuentaRepository;
import com.usuario.usuarioservice.Repository.UsuarioRepository;
import com.usuario.usuarioservice.Service.Service;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@ResponseStatus(HttpStatus.OK)
@RequestMapping("/microservicio_usuario")
public class ServiceController {
    @Autowired
    private Service service;
    @Autowired
    CuentaRepository cuentaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;


    private boolean isAdmin(int id){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null && "ADMIN".equals(usuario.getRol())) {
            return true;
        }
        return false;
    }
    private boolean isUser(int id){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null && "USER".equals(usuario.getRol())) {
            return true;
        }
        return false;
    }
    private boolean isMantenimiento(int id){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null && "MANTENIMIENTO".equals(usuario.getRol())) {
            return true;
        }
        return false;
    }


    /**
     * modifica una cuenta para que aparezca como estado suspendido y agrega un motivo del porque se suspendió la cuenta
     * @param idLog id de la cuenta logueada
     * @param cuenta cuenta que se suspenderá (cuenta en estado true = cuenta activa, cuenta en estado false = cuenta suspendida)
     * @param motivo motivo de suspención
     */

    @PutMapping("/suspenderCuenta/{idLog}/{motivo}")
    @Transactional
    public void suspenderCuenta(@RequestBody Cuenta cuenta, @PathVariable int idLog, @PathVariable String motivo) {
        if (isAdmin(idLog)) {
            cuentaRepository.suspenderCuenta(cuenta.getId(), motivo);
        }
    }

    @PostMapping("/agregarMonopatin/{idLog}")
    @Transactional
    public void agregarMonopatin(@PathVariable int idLog, @RequestBody MonopatinDTO monopatin){
        if (isAdmin(idLog)){
            service.agregarMonopatin(monopatin);
        }
    }
    @PostMapping("/agregarParada/{idLog}")
    @Transactional
    public void agregarParada(@PathVariable int idLog, @RequestBody ParadaDTO parada){
        if (isAdmin(idLog)){
            service.agregarParada(parada);
        }
    }

    @GetMapping("/monopatinesEstados/{idLog}")
    public ResponseEntity<String> consultaMonopatines(@PathVariable int idLog){
        if (isAdmin(idLog)){
           return service.consultaMonopatines();
        }
        return null;
    }

    @GetMapping("/monopatinesCerca/{id}/{ubicacion}")
    public ResponseEntity<List<MonopatinDTO>> monopatinesCercanos(@PathVariable int id, @PathVariable String ubicacion) {
        if (isUser(id)) {
          return service.monopatinesCercanos(ubicacion);
        }
        return null;
    }

    @PutMapping("/actualizacionDeTarifa/{idLog}/{fecha}/{idTarifa}/{valor}")
    public ResponseEntity<String> modificarTarifaEnFecha(@PathVariable int idLog, @PathVariable String fecha, @PathVariable String idTarifa, @PathVariable String valor){
        if (isAdmin(idLog)){
            LocalDate f = LocalDate.parse(fecha);
            return service.modificarTarifaEnFecha(f,idTarifa,valor);
        }else {
            return ResponseEntity.badRequest().body("No es admin.");
        }
    }

   /* @GetMapping("/monopatinesConViajeEnAnio/idLog/{}/{}")
    public ResponseEntity<List<MonopatinDTO>> monopatinesConCantViajesEnAnio(@PathVariable int idLog,) {
        if (verificaRol(idLog)) {
            return service.monopatinesConCantViajesEnAnio();
        }
        return null;

    }
    */
}
