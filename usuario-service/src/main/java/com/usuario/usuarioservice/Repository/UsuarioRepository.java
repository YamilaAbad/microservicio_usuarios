package com.usuario.usuarioservice.Repository;

import com.usuario.usuarioservice.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario>findByUsername(String username);
    @Query("SELECT u FROM Usuario u where u.email = :email")
    Usuario findByEmail(String email);
    @Query("SELECT u FROM Usuario u WHERE u.rol = 'ADMIN' AND u.id = :idLog")
    Usuario esAdmin(int idLog);
}
