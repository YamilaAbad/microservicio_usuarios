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

    private String nombre;
    private String apellido;
    private String rol;
    private boolean estado;
    private int num_de_celular;
    private String email;
    @ManyToMany(mappedBy="usuario", fetch = FetchType.LAZY)
    private List<Cuenta> cuentas_asociadas;
}
