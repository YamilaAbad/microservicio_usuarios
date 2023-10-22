package com.usuario.usuarioservice.Controller;

import com.usuario.usuarioservice.Model.CuentasDeUsuario;
import com.usuario.usuarioservice.Repository.CuentasDeUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/microservicio_usuario")
public class CuentasDeUsuarioController {

    @Autowired
    private CuentasDeUsuarioRepository cuentasDeUsuarioRepository;
    //retorna todas las cuentas de usuarios
    @GetMapping("/cuentasDeUsuario")
    @ResponseStatus(HttpStatus.OK)
    public List<CuentasDeUsuario> getAllCuentasDeUsuario(){
        return cuentasDeUsuarioRepository.findAll();
    }
    //retorna una cuenta de usuario por id
    @GetMapping("/cuentasDeUsuario/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CuentasDeUsuario getCuentasDeUsuario(@PathVariable int id){
        return cuentasDeUsuarioRepository.findById(id).orElse(null);
    }

    //asocia una cuenta con un usuario (crea una cuenta de usuario)
    @PostMapping("/asociarCuenta")
    @ResponseStatus(HttpStatus.OK)
    public void asociarCuentasDeUsuario(@RequestBody CuentasDeUsuario cuentasDeUsuario){
        cuentasDeUsuarioRepository.save(cuentasDeUsuario);
    }

}
