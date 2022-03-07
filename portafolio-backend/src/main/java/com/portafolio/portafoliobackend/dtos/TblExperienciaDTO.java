package com.portafolio.portafoliobackend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TblExperienciaDTO {

    // CAMPO IDENTIFICADOR DE EXPERIENCIA
    private Long idExperiencia;

    // IDENTIFICADOR INTERNO DEL PERFIL
    private Long idPerfil;

    // DESCRIBIR TU EXPERIENCIA
    private String deExperiencia;
}
