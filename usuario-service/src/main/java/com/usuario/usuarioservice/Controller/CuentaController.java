package com.usuario.usuarioservice.Controller;

import com.usuario.usuarioservice.Model.Cuenta;
import com.usuario.usuarioservice.Repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/microservicio_usuario")
public class CuentaController {
    @Autowired
    private CuentaRepository cuentaRepository;

    /**
     * retorna todas las cuentas
     * @return lista de cuentas
     */
    @GetMapping("/cuentas")
    @ResponseStatus(HttpStatus.OK)
    public List<Cuenta> getAllCuentas(){
        return cuentaRepository.findAll();
    }

    /**
     * retorna una cuenta por id
     * @param id
     * @return cuenta correspondiente al id o null si no se cuentra
     */
    @GetMapping("/cuenta/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cuenta getCuenta(@PathVariable int id){
        return cuentaRepository.findById(id).orElse(null);
    }

    /**
     * crea una cuenta (el usuario debe ser unico en la BD)
     * @param cuenta
     */
    @PostMapping("/crearCuenta")
    @ResponseStatus(HttpStatus.OK)
    public void crearCuenta(@RequestBody Cuenta cuenta){
        if (cuentaRepository.getByUser(cuenta.getUser()) == null){
            cuentaRepository.save(cuenta);
        }
    }

    @PutMapping("/cargarSaldo/{s}")
    @ResponseStatus(HttpStatus.OK)
    public void cargarSaldo(@RequestBody Cuenta cuenta, @PathVariable float s){
        cuentaRepository.cargarSaldo(cuenta.getId(), s);
    }

}
