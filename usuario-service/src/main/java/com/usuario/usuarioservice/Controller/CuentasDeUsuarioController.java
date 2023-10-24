package com.usuario.usuarioservice.Controller;

import com.usuario.usuarioservice.Model.Cuenta;
import com.usuario.usuarioservice.Model.CuentasDeUsuario;
import com.usuario.usuarioservice.Model.Usuario;
import com.usuario.usuarioservice.Repository.CuentasDeUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/microservicio_usuario")
public class CuentasDeUsuarioController {

    @Autowired
    CuentasDeUsuarioRepository cuentasDeUsuarioRepository;

    @PostMapping("/enlazarCuenta/{user}/{pass}")
    @ResponseStatus(HttpStatus.OK)
    public void enlazarCuenta(@RequestBody Usuario usuario, @RequestParam String user, @RequestParam String pass){
        Cuenta cuenta = cuentasDeUsuarioRepository.enlazarCuenta(usuario,user,pass);
        if( cuenta != null){
            CuentasDeUsuario relacion = new CuentasDeUsuario();
            relacion.setUsuario(usuario);
            relacion.setCuenta(cuenta);
            cuentasDeUsuarioRepository.save(relacion);
        }

    }

}
