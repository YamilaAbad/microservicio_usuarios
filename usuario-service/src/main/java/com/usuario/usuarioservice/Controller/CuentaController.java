package com.usuario.usuarioservice.Controller;

import com.usuario.usuarioservice.DTO.CuentaDTO;
import com.usuario.usuarioservice.Model.Cuenta;
import com.usuario.usuarioservice.Repository.CuentaRepository;
import com.usuario.usuarioservice.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/microservicio_usuario")
public class CuentaController {
    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private UsuarioService usuarioService;

    /**
     * retorna todas las cuentas
     * @return lista de cuentas
     */
    @GetMapping("/cuentas")
    @PreAuthorize("hasAuthority('ADMIN')")
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
    @PreAuthorize("hasAuthority('USER')")
    @ResponseStatus(HttpStatus.OK)
    public void crearCuenta(@RequestBody CuentaDTO cuenta){
        if (cuentaRepository.getByUser(cuenta.getUser()) == null){
            usuarioService.crearCuenta(cuenta);
        }
    }

    @PutMapping("/cargarSaldo/{s}")
    @PreAuthorize("hasAuthority('USER')")
    @ResponseStatus(HttpStatus.OK)
    public void cargarSaldo(@RequestBody Cuenta cuenta, @PathVariable float s){
        cuentaRepository.cargarSaldo(cuenta.getId(), s);
    }

}
