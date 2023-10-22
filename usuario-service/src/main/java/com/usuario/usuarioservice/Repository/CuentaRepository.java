package com.usuario.usuarioservice.Repository;

import com.usuario.usuarioservice.Model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {
    @Modifying
    @Query("DELETE FROM Cuenta c WHERE c.id = :id")
    void anularCuenta(@Param("id") int id);
}
