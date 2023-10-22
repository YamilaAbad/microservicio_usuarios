package com.usuario.usuarioservice.Repository;

import com.usuario.usuarioservice.Model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {

}
