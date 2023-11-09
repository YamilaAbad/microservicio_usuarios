package com.usuario.usuarioservice.Model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Esta clase está dedicada a la creación de la entidad usuario.
 *
 * El atributo de tipo boolean "estado" no hace referencia a la tabla estado,
 * éste solo se utiliza para saber si un usuario está activo o no.
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private int id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    @Column(nullable = false)
    private boolean estado;
    @Column(nullable = false)
    private String num_de_celular;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Getter(AccessLevel.NONE)
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<CuentasDeUsuario> cuentas_asociados;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((rol.name())));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
