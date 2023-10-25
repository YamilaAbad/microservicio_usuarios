package com.usuario.usuarioservice.Service;


import com.usuario.usuarioservice.DTO.CuentaDTO;
import com.usuario.usuarioservice.Repository.CuentaRepository;
import com.usuario.usuarioservice.Repository.CuentasDeUsuarioRepository;
import com.usuario.usuarioservice.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


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

    /**
     * Solo se mostrará la lista de cuentas asociadas al usuario con id pasado por parámetro.
     * de la cuenta solo se mostrará el atributo user.
     * @param id
     * @return
     */
    public List<CuentaDTO> cuentasDelUsuario (int id){
        return cuentasDeUsuarioRepository.cuentasDelUsuarioID(id);
    }

}
