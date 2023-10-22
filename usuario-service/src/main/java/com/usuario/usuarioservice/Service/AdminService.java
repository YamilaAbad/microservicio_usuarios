package com.usuario.usuarioservice.Service;

import com.usuario.usuarioservice.Model.Usuario;
import com.usuario.usuarioservice.Repository.CuentaRepository;
import com.usuario.usuarioservice.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final CuentaRepository cuentaRepository;
    private final UsuarioRepository usuarioRepository;
    private final Usuario usuario;
    public AdminService(CuentaRepository cuentaRepository, UsuarioRepository usuarioRepository, Usuario usuario) {
        this.usuario=usuario;
        this.cuentaRepository = cuentaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public void anularCuenta(int id){
        if (this.usuario.getRol()=="admin"){
            cuentaRepository.anularCuenta(id);
        }
    }
}
