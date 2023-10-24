package com.usuario.usuarioservice.Service;

import com.usuario.usuarioservice.Model.Cuenta;
import com.usuario.usuarioservice.Model.Usuario;
import com.usuario.usuarioservice.Repository.CuentaRepository;
import com.usuario.usuarioservice.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
public class AdminService {
    @Autowired
    private final CuentaRepository cuentaRepository;
    @Autowired
    private final UsuarioRepository usuarioRepository;

    public AdminService(CuentaRepository cuentaRepository, UsuarioRepository usuarioRepository) {
        this.cuentaRepository = cuentaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * anula (borra) una cuenta de usuario que es pasada por parametro
     * @param idLogg id de la cuenta logueada
     * @param cuenta cuenta que será borrada
     */
    @ResponseStatus(HttpStatus.OK)
    public void anularCuenta(int idLogg, Cuenta cuenta){
       Usuario usuario = usuarioRepository.findById(idLogg).orElse(null);
        if (usuario != null && "admin".equals(usuario.getRol())){
            cuentaRepository.anularCuenta(cuenta.getId());
        }else {
            throw new RuntimeException("No tiene permisos.");
        }
    }

    /**
     * modifica una cuenta para que aparezca como estado suspendido y agrega un motivo del porque se suspendió la cuenta
     * @param idLogg id de la cuenta logueada
     * @param cuenta cuenta que se suspenderá (cuenta en estado true = cuenta activa, cuenta en estado false = cuenta suspendida)
     * @param motivo motivo de suspención
     */
    @ResponseStatus(HttpStatus.OK)
    public void suspenderCuenta(int idLogg, Cuenta cuenta, String motivo){
        Usuario usuario = usuarioRepository.findById(idLogg).orElse(null);
        if (usuario != null && "admin".equals(usuario.getRol())){
            cuentaRepository.modificarCuenta(cuenta.getId(),motivo);
        }else {
            throw new RuntimeException("No tiene permisos.");
        }
    }

}
