package com.usuario.usuarioservice.Model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;
/**
 * Esta clase está dedicada a la creación de la entidad cuenta.
 *
 * El atributo de tipo boolean "estado" no hace referencia a la tabla estado,
 * éste solo se utiliza para saber si una cuenta está activa o no.
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cuenta {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idCuenta")
    private int id;
    @Column(nullable = false)
    private String user;
    @Column(nullable = false)
    private String pass;
    @Column(nullable = false)
    private Timestamp fecha_de_creacion;
    @Column(nullable = false)
    private float saldo;
    private float total_de_gasto;
    @Column(nullable = false)
    private boolean estado;
    private String motivo_de_suspencion;
    @Getter(AccessLevel.NONE)
    @OneToMany(mappedBy = "cuenta", fetch = FetchType.LAZY)
    private List<CuentasDeUsuario> usuarios_asociados;

}
