package com.usuario.usuarioservice.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CuentasDeUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idCuentasDeUsuario")
    private int id;

    @ManyToMany
    @JoinTable(
            name = "CuentasDeUsuario_Cuenta",
            joinColumns = @JoinColumn(name = "CuentasDeUsuario_idCuentasDeUsuario"),
            inverseJoinColumns = @JoinColumn(name = "Usuario_idUsuario")
    )
    private List<Usuario> usuario;

    @ManyToMany
    @JoinTable(
            name = "CuentasDeUsuario_Cuenta",
            joinColumns = @JoinColumn(name = "CuentasDeUsuario_idCuentasDeUsuario"),
            inverseJoinColumns = @JoinColumn(name = "Cuenta_idCuenta")
    )
    private List<Cuenta> cuentas;

}
