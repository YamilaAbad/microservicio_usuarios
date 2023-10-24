package com.usuario.usuarioservice.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class UsuarioDTO {

    private String nombre;
    private String apellido;
    private String rol;
    private boolean estado;
    private int num_de_celular;
    private String email;
}
