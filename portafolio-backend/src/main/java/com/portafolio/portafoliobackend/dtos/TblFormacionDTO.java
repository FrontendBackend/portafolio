package com.portafolio.portafoliobackend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TblFormacionDTO {

    // CAMPO IDENTIFICADOR DE FORMACION
    private Long idFormacion;

    // IDENTIFICADOR INTERNO DEL PERFIL
    private Long idPerfil;

    // DESCRIBIR TU FORMACION
    private String deFormacion;
}
