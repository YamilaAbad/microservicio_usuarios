package com.usuario.usuarioservice.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
public class ViajeDTO {
    private int idViaje;
    private String idMonopatin;
    private String origenDelViaje;
    private String destinoDelViaje;
    private Time horaInicioViaje;
    private Time horaFinViaje;
    private Timestamp fechaDelViaje;
    private boolean isFinalizado;
    private double cobroViaje;
}
