package com.portafolio.portafoliobackend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TblEducacionDTO {
    
    // CAMPO IDENTIFICADOR DE EDUCACION
    private Long idEducacion;

    // IDENTIFICADOR INTERNO DEL PERFIL
    private Long idPerfil;

    // DESCRIBIR TU EDUCACIÓN
    private String deEducacion;
}
