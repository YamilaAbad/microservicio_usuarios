package com.usuario.usuarioservice.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MonopatinDTO {
    private int km_totales;
    private String ubicacion;
    private int km_recorridos;
    private EstadoDTO estado;
}
