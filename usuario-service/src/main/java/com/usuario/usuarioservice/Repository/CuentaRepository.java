package com.usuario.usuarioservice.Repository;

import com.usuario.usuarioservice.Model.Cuenta;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {
    @Modifying
    @Query("DELETE FROM Cuenta WHERE id = :id")
    void borrarCuenta(int id);
    @Modifying
    @Query("UPDATE Cuenta c SET c.estado = false, c.motivo_de_suspencion = :motivo WHERE c.id = :id")
    void suspenderCuenta(int id, String motivo);
    @Query("SELECT c FROM Cuenta c WHERE c.user = :user AND c.pass = :pass")
    public Cuenta buscarCuenta(String user,String pass);
    @Query("SELECT c FROM Cuenta c WHERE c.user = :user")
    Cuenta getByUser(String user);
    @Transactional
    @Modifying
    @Query("UPDATE Cuenta c SET c.saldo = c.saldo + :s WHERE c.id = :id")
    void cargarSaldo(@Param("id") int id, @Param("s") float s);

}
