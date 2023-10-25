package com.usuario.usuarioservice.DTO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
@NoArgsConstructor
public class CuentaDTO {

    private String user;
    private String pass;
    private Timestamp fecha_de_creacion;
    private float saldo;
    private float total_de_gasto;
    private boolean estado;
    private String motivo_de_suspencion;

    public CuentaDTO(String user) {
        this.user = user;
    }

}
