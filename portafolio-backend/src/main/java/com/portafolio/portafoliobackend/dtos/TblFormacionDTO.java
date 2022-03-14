package com.portafolio.portafoliobackend.dtos;

import java.util.Date;

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

    /** ---------- AUDITORIA ------------------------------------------ **/

    // CAMPO ESTADO DEL REGISTRO. LOS POSIBLES VALORES SON: "1" = ACTIVO Y "0" =
    // INACTIVO
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
