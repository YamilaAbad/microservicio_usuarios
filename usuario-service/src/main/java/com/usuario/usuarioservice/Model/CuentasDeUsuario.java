package com.usuario.usuarioservice.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CuentasDeUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idCuentasDeUsuario")
    private int id;

    @ManyToOne
    @JoinColumn(name = "Usuario_idUsuario")
    private Usuario usuario;

    @ManyToMany
    @JoinColumn(name = "Cuenta_idCuenta")
    private Cuenta cuenta;



}
