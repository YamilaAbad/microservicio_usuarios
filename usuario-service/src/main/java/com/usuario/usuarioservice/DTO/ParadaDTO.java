package com.usuario.usuarioservice.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ParadaDTO {
    private String nombre;
    private String ubicacion;
    private String estado;
    private List<MonopatinDTO> monopatin = new ArrayList<>();


}
