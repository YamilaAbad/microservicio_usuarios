package com.usuario.usuarioservice.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class CuentasDeUsuario {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="idCeuntasDeUsuario")
        private int id;

        @ManyToOne
        @JoinColumn(name = "Cuenta_idCuenta")
        private Cuenta cuenta;

        @ManyToOne
        @JoinColumn(name = "Usuario_idUsuario")
        private Usuario usuario;


}
