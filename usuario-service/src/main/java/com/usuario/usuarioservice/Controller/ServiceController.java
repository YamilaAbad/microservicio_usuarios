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
import org.springframework.security.access.prepost.PreAuthorize;
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

    /**
     * modifica una cuenta para que aparezca como estado suspendido y agrega un motivo del porque se suspendió la cuenta
     * @param cuenta cuenta que se suspenderá (cuenta en estado true = cuenta activa, cuenta en estado false = cuenta suspendida)
     * @param motivo motivo de suspención
     */

    @PutMapping("/suspenderCuenta/{motivo}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional
    public void suspenderCuenta(@RequestBody Cuenta cuenta,@PathVariable String motivo) {
        cuentaRepository.suspenderCuenta(cuenta.getId(), motivo);
    }

    @PostMapping("/agregarMonopatin/{idLog}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional
    public void agregarMonopatin(@RequestBody MonopatinDTO monopatin){
            service.agregarMonopatin(monopatin);
    }
    @PostMapping("/agregarParada")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional
    public void agregarParada(@RequestBody ParadaDTO parada){
            service.agregarParada(parada);
    }

    @GetMapping("/monopatinesEstados")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> consultaMonopatines(){
        return service.consultaMonopatines();
    }

    @GetMapping("/monopatinesCerca/{ubicacion}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<MonopatinDTO>> monopatinesCercanos(@PathVariable String ubicacion) {
          return service.monopatinesCercanos(ubicacion);
    }

    @PutMapping("/actualizacionDeTarifa/{fecha}/{idTarifa}/{valor}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> modificarTarifaEnFecha(@PathVariable String fecha, @PathVariable String idTarifa, @PathVariable String valor){
        LocalDate f = LocalDate.parse(fecha);
        return service.modificarTarifaEnFecha(f,idTarifa,valor);
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
