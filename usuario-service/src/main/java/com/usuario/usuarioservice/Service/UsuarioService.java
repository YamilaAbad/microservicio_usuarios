package com.usuario.usuarioservice.Service;


import com.usuario.usuarioservice.DTO.CuentaDTO;
import com.usuario.usuarioservice.Model.Cuenta;
import com.usuario.usuarioservice.Model.CuentasDeUsuario;
import com.usuario.usuarioservice.Model.Usuario;
import com.usuario.usuarioservice.Repository.CuentaRepository;
import com.usuario.usuarioservice.Repository.CuentasDeUsuarioRepository;
import com.usuario.usuarioservice.Repository.UsuarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class UsuarioService {
    @Autowired
    private final CuentasDeUsuarioRepository cuentasDeUsuarioRepository;
    @Autowired
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private final CuentaRepository cuentaRepository;
    @Autowired
    EntityManager entityManager;
    public UsuarioService(CuentasDeUsuarioRepository cuentasDeUsuarioRepository, UsuarioRepository usuarioRepository, CuentaRepository cuentaRepository) {
        this.cuentasDeUsuarioRepository = cuentasDeUsuarioRepository;
        this.usuarioRepository = usuarioRepository;
        this.cuentaRepository = cuentaRepository;
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
