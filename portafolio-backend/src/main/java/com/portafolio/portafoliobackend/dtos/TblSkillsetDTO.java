package com.portafolio.portafoliobackend.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class TblSkillsetDTO {

    // ID DE SKILLSET
    private Long idSkillset;

    // ID DE SKILLSET
    private Long idPerfil;

    // NOMBRE DE SKILLSET
    private String noSkillset;

    // FOTO O IMAGEN DE SKILLSET
    private String fotoSkillset;

    // NOMBRE ORIGINAL DE LA IMAGEN
    private String filename;

    // TIPO DE IMAGEN
    private String filetype;

    // NOMBRE DE LA IMAGEN
    private byte[] value;

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
