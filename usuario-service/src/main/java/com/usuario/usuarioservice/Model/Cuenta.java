package com.usuario.usuarioservice.Model;

import jakarta.persistence.*;
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

    private String user;
    private String pass;
    private Timestamp fecha_de_creacion;
    private float saldo;
    private float historial_de_gasto;
    private boolean estado;
    private String motivo_de_suspencion;
    @ManyToMany(mappedBy="cuenta", fetch = FetchType.LAZY)
    private List<Usuario> usuarios_asociados;
}
