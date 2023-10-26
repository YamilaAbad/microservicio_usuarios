package com.usuario.usuarioservice.Controller;
import com.usuario.usuarioservice.DTO.MonopatinDTO;
import com.usuario.usuarioservice.DTO.ParadaDTO;
import com.usuario.usuarioservice.Model.Cuenta;
import com.usuario.usuarioservice.Model.Usuario;
import com.usuario.usuarioservice.Repository.CuentaRepository;
import com.usuario.usuarioservice.Repository.UsuarioRepository;
import com.usuario.usuarioservice.Service.AdminService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseStatus(HttpStatus.OK)
@RequestMapping("/microservicio_usuario")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    CuentaRepository cuentaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;


    private boolean verificaRol(int id){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null && "admin".equals(usuario.getRol())) {
            return true;
        }
        return false;
    }
    /**
     * anula (borra) una cuenta de usuario que es pasada por parametro
     * @param idLogg id de la cuenta logueada
     * @param cuenta cuenta que será borrada
     */
    @DeleteMapping("/borrarCuenta/{idLogg}")
    @Transactional
    public void borrarCuenta(@PathVariable int idLogg, @RequestBody Cuenta cuenta){
       if(verificaRol(idLogg)){
            cuentaRepository.borrarCuenta(cuenta.getId());
        }
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
        if (verificaRol(idLog)) {
            cuentaRepository.suspenderCuenta(cuenta.getId(), motivo);
        }
    }

    @PostMapping("/agregarMonopatin/{idLog}")
    @Transactional
    public void agregarMonopatin(@PathVariable int idLog, @RequestBody MonopatinDTO monopatin){
        if (verificaRol(idLog)){
            adminService.agregarMonopatin(monopatin);
        }
    }
    @PostMapping("/agregarParada/{idLog}")
    @Transactional
    public void agregarParada(@PathVariable int idLog, @RequestBody ParadaDTO parada){
        if (verificaRol(idLog)){
            adminService.agregarParada(parada);
        }
    }

    @GetMapping("/monopatinesEstados/{idLog}")
    public ResponseEntity<String> consultaMonopatines(@PathVariable int idLog){
        if (verificaRol(idLog)){
           return adminService.consultaMonopatines();
        }
        return null;
    }
}
