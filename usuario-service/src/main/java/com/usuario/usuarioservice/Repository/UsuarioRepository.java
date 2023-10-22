package com.usuario.usuarioservice.Repository;

import com.usuario.usuarioservice.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query("SELECT u FROM Usuario u where u.email = :email")
    public Usuario findByEmail(String email);
}
