package com.usuario.usuarioservice.Service;

import com.usuario.usuarioservice.Model.Cuenta;
import com.usuario.usuarioservice.Model.Usuario;
import com.usuario.usuarioservice.Repository.CuentaRepository;
import com.usuario.usuarioservice.Repository.CuentasDeUsuarioRepository;
import com.usuario.usuarioservice.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class UsuarioService {
    @Autowired
    private final CuentaRepository cuentaRepository;
    @Autowired
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private final CuentasDeUsuarioRepository cuentasDeUsuarioRepository;

    public UsuarioService(CuentaRepository cuentaRepository, UsuarioRepository usuarioRepository, CuentasDeUsuarioRepository cuentasDeUsuarioRepository) {
        this.cuentaRepository = cuentaRepository;
        this.usuarioRepository = usuarioRepository;
        this.cuentasDeUsuarioRepository = cuentasDeUsuarioRepository;
    }

    public Cuenta enlazarCuenta(Usuario usuario, String user, String pass) throws Exception {
        try {
            Cuenta cuenta = cuentaRepository.buscarCuenta(user,pass);
            if(cuenta!=null){
                cuentasDeUsuarioRepository.enlazarCuenta(usuario,user,pass);
                return cuenta;
            }else
                return null;
            } catch (Exception e) {
                throw new Exception("La cuenta no pudo ser encontrada.");
            }
    }
}
