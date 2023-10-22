package com.usuario.usuarioservice.Controller;

import com.usuario.usuarioservice.Model.Cuenta;
import com.usuario.usuarioservice.Repository.CuentaRepository;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/microservicio_usuario")
public class CuentaController {

    @Autowired
    private CuentaRepository cuentaRepository;

    //retorna todas las cuentas
    @GetMapping("/cuentas")
    @ResponseStatus(HttpStatus.OK)
    public List<Cuenta> getAllCuentas(){
        return cuentaRepository.findAll();
    }

    //retorna una cuenta por id
    @GetMapping("/cuenta/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cuenta getCuenta(@PathVariable int id){
        return cuentaRepository.findById(id).orElse(null);
    }

    //crea una cuenta
    @PostMapping("/crearCuenta")
    @ResponseStatus(HttpStatus.OK)
    public void crearCuenta(@RequestBody Cuenta cuenta){

    }
}
