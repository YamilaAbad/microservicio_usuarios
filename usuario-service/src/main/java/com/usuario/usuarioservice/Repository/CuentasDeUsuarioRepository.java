package com.usuario.usuarioservice.Repository;

import com.usuario.usuarioservice.Model.CuentasDeUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentasDeUsuarioRepository extends JpaRepository<CuentasDeUsuario, Integer> {
}
