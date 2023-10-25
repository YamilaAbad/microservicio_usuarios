package com.usuario.usuarioservice.Controller;

import com.usuario.usuarioservice.Model.Cuenta;
import com.usuario.usuarioservice.Model.Usuario;
import com.usuario.usuarioservice.Repository.CuentaRepository;
import com.usuario.usuarioservice.Repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/microservicio_usuario")
public class AdminController {

    @Autowired
    CuentaRepository cuentaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;


    /**
     * anula (borra) una cuenta de usuario que es pasada por parametro
     * @param idLogg id de la cuenta logueada
     * @param cuenta cuenta que será borrada
     */
    @DeleteMapping("/borrarCuenta/{idLogg}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void borrarCuenta(@PathVariable int idLogg, @RequestBody Cuenta cuenta){
        Usuario usuario = usuarioRepository.findById(idLogg).orElse(null);
        if (usuario != null && "admin".equals(usuario.getRol())){
            cuentaRepository.borrarCuenta(cuenta.getId());
        }else {
            throw new RuntimeException("No tiene permisos.");
        }
    }

    /**
     * modifica una cuenta para que aparezca como estado suspendido y agrega un motivo del porque se suspendió la cuenta
     * @param idLog id de la cuenta logueada
     * @param cuenta cuenta que se suspenderá (cuenta en estado true = cuenta activa, cuenta en estado false = cuenta suspendida)
     * @param motivo motivo de suspención
     */

    @PutMapping("/suspenderCuenta/{idLog}/{motivo}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void suspenderCuenta(@RequestBody Cuenta cuenta, @PathVariable int idLog, @PathVariable String motivo) {
        Usuario usuario = usuarioRepository.findById(idLog).orElse(null);
        if (usuario != null && "admin".equals(usuario.getRol())){
            cuentaRepository.suspenderCuenta(cuenta.getId(),motivo);
        }else {
            throw new RuntimeException("No tiene permisos.");
        }
    }
}
