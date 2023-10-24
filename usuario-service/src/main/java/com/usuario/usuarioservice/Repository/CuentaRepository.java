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
    @Query("DELETE FROM Cuenta WHERE id = :id")
    void anularCuenta(@Param("id") int id);
    @Query("UPDATE Cuenta SET estado = false, motivo_de_suspencion = :motivo WHERE id = :id")
    void modificarCuenta(int id, String motivo);
    @Query("SELECT c FROM Cuenta c WHERE c.user = :user AND c.pass = :pass")
    Cuenta buscarCuenta(String user, String pass);
    @Query("SELECT c FROM Cuenta c WHERE c.user = :user")
    Cuenta getByUser(String user);
}
