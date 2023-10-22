package com.usuario.usuarioservice.Service;

import com.usuario.usuarioservice.Model.Cuenta;
import com.usuario.usuarioservice.Model.Usuario;
import com.usuario.usuarioservice.Repository.CuentaRepository;
import com.usuario.usuarioservice.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final CuentaRepository cuentaRepository;
    private final UsuarioRepository usuarioRepository;

    public AdminService(CuentaRepository cuentaRepository, UsuarioRepository usuarioRepository) {
        this.cuentaRepository = cuentaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public void anularCuenta(int idLogg, Cuenta cuenta){
       Usuario usuario = usuarioRepository.findById(idLogg).orElse(null);
        if (usuario.getRol()=="admin"){
            cuentaRepository.anularCuenta(cuenta.getId());
        }
    }
}
