package com.usuario.usuarioservice.Repository;

import com.usuario.usuarioservice.Model.Cuenta;
import com.usuario.usuarioservice.Model.CuentasDeUsuario;
import com.usuario.usuarioservice.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CuentasDeUsuarioRepository extends JpaRepository<CuentasDeUsuario, Integer> {

    Cuenta enlazarCuenta(Usuario usuario, String user, String pass);

}
