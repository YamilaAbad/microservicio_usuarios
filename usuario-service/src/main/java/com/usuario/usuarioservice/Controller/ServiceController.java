package com.usuario.usuarioservice.Controller;
import com.usuario.usuarioservice.DTO.MonopatinDTO;
import com.usuario.usuarioservice.DTO.ParadaDTO;
import com.usuario.usuarioservice.DTO.ViajeDTO;
import com.usuario.usuarioservice.Model.Cuenta;
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

    @PostMapping("/agregarMonopatin")
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
    public ResponseEntity<String> monopatinesCercanos(@PathVariable String ubicacion) {
          return service.monopatinesCercanos(ubicacion);
    }

    @GetMapping("/reporteMonopatinesPorKM/{km}")
    @PreAuthorize("hasAuthority('MANTENIMIENTO')")
    public ResponseEntity<String> reportePorKM(@PathVariable int km){
        return service.reportePorKM(km);
    }

    @GetMapping("/monopatines")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> listaDeMonopatines(){
        return service.listaMonopatines();
    }
    @GetMapping("/monopatin/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> infoMonopatin(@PathVariable String id){
        return service.infoMonopatin(id);
    }

    @PutMapping("/actualizacionDeTarifa/{fecha}/{idTarifa}/{valor}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> modificarTarifaEnFecha(@PathVariable String fecha, @PathVariable String idTarifa, @PathVariable String valor){
        LocalDate f = LocalDate.parse(fecha);
        return service.modificarTarifaEnFecha(f,idTarifa,valor);
    }

    @DeleteMapping("/eliminarMonopatin/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void eliminarMonopatin(@PathVariable String id){
        service.eliminarMonopatin(id);
    }

    @GetMapping("/paradas")
    public ResponseEntity<String> listaDeParadas(){
        return service.listaParadas();
    }

    @GetMapping("/parada/{id}")
    public ResponseEntity<String> infoParada(@PathVariable String id){
        return service.infoParada(id);
    }

    @GetMapping("/monopatinesEnParada/{ubicacion}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> monopatinEnUbicacionParada(@PathVariable String ubicacion){
        return service.monopatinEnUbicacionParada(ubicacion);
    }
    @GetMapping("/paradasEnUbicacion/{ubicacion}")
    public ResponseEntity<String> paradasEnLaUbicacion(@PathVariable String ubicacion){
        return service.paradasEnUbicacion(ubicacion);
    }

    @PutMapping("/actualizarParada/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void actualizarParada(@PathVariable String id, @RequestBody ParadaDTO paradaDTO){
        service.actualizarParada(id,paradaDTO);
    }

    @PutMapping("/agregarMonopatin/{ubicacion}")
    public void agregarMonopatinAparada(@PathVariable String ubicacion, @RequestBody MonopatinDTO monopatin){
        service.agregarMonopatinAparada(ubicacion,monopatin);
    }

    @DeleteMapping("/eliminarParada/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String eliminarParada(@PathVariable String id){
        return service.eliminarParada(id);
    }

   @PostMapping("/iniciarViaje/{id}")
   @PreAuthorize("hasAuthority('USER')")
   public void iniciarViaje(@PathVariable int id, @RequestBody ViajeDTO viajeDTO){
       service.iniciarViaje(id, viajeDTO);
   }
   @GetMapping("/monopatinesConMasDeXViajesEnAnio/{x}/{anio}")
   @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String>obtenerMonopatinConMasViajesEnAnio(@PathVariable int x, @PathVariable int anio){
        return service.obtenerMonopatinConMasViajesEnAnio(x, anio);
   }
    @GetMapping("/reportePorKilometro/{kmParaMantenimiento}")
    @PreAuthorize("hasAuthority('MANTENIMIENTO')")
    public ResponseEntity<String> obtenerReporteMonopatinesPorKm(@PathVariable int kmParaMantenimiento) {
        return service.obtenerReporteMonopatinesPorKm( kmParaMantenimiento);
    }

    @GetMapping("/facturacion/{anio}/{mesInicio}/{mesFin}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> facturacionFiltro(@PathVariable int anio, @PathVariable int mesInicio, @PathVariable int mesFin){
        return service.facturacionFiltro(anio,mesInicio,mesFin);
    }

}
