package com.usuario.usuarioservice.Repository;

import com.usuario.usuarioservice.DTO.CuentaDTO;
import com.usuario.usuarioservice.DTO.UsuarioDTO;
import com.usuario.usuarioservice.Model.Cuenta;
import com.usuario.usuarioservice.Model.CuentasDeUsuario;
import com.usuario.usuarioservice.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CuentasDeUsuarioRepository extends JpaRepository<CuentasDeUsuario, Integer> {


    @Query("SELECT NEW com.usuario.usuarioservice.DTO.CuentaDTO(c.user) " +
            "FROM CuentasDeUsuario cu " +
            "JOIN cu.cuenta c " +
            "JOIN cu.usuario u " +
            "WHERE u.id = :id")
    List<CuentaDTO> cuentasDelUsuarioID(int id);
}
