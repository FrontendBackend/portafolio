package com.portafolio.portafoliobackend.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TblPortafolioDTO {
    
    // CAMPO IDENTIFICADOR DEL PORTAFOLIO
    private Long idPortafolio;

    // CAMPO NOMBRE DE PORTAFOLIO
    private String noPortafolio;

    // CAMPO DESCRIPCIÓN DEL PORTAFOLIO
    private String dePortafolio;
    
    // CAMPO IMAGEN DEL PORTAFOLIO
    private String imgPortafolio;

    // CAMPO ESTADO DEL REGISTRO. LOS POSIBLES VALORES SON: "1" = ACTIVO Y "0" = INACTIVO
    private String esRegistro;

    // CAMPO USUARIO CREADOR
    private String usCreacion;

    // CAMPO TERMINAL DE CREACIÓN
    private String ipCreacion;

    // CAMPO FECHA Y HORA DE CREACIÓN
    private Date feCreacion;

    // CAMPO USUARIO MODIFICADOR
    private String usActualizacion;

    // CAMPO TERMINAL DE MODIFICACIÓN
    private String ipActualizacion;

    // CAMPO FECHA Y HORA DE MODIFICACIÓN
    private Date feActualizacion;
}
