package com.usuario.usuarioservice.Model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class Usuario {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private int id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido;
    @Column(nullable = false)
    private String rol;
    @Column(nullable = false)
    private boolean estado;
    @Column(nullable = false)
    private int num_de_celular;
    @Column(nullable = false)
    private String email;
    @ManyToMany(mappedBy = "usuario")
    private List<CuentasDeUsuario> cuentas_asociadas;
}
