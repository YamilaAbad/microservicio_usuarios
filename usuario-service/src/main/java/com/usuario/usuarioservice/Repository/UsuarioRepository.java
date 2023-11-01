package com.usuario.usuarioservice.Repository;

import com.usuario.usuarioservice.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query("SELECT u FROM Usuario u where u.email = :email")
    Usuario findByEmail(String email);
    @Query("SELECT u FROM Usuario u WHERE u.rol = 'admin' AND u.id = :idLog")
    Usuario esAdmin(int idLog);

}
