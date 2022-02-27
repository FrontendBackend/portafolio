package com.portafolio.portafoliobackend.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TblPerfilDTO {

    // CAMPO IDENTIFICADOR DE PERFIL
    private Long idPerfil;

    // CAMPO IDENTIFICADOR DE UBIGEO NACIMIENTO DEL PERFIL
    private Long idUbigeo;

    // CAMPO NUMERO DE DNI DEL PERFIL
    private Long nuDniPerfil;

    // CAMPO NOMBRE DE PERFIL
    private String noPerfil;

    // CAMPO APELLIDOS DEL PERFIL
    private String apPerfil;

    // CAMPO DIRECCIÓN DEL PERFIL
    private String dirPerfil;

    // CAMPO TELEFONO DEL PERFIL
    private Long telPerfil;

    // CAMPO CORREO ELECTRONICO DEL PERFIL
    private String emailPerfil;

    // CAMPO FECHA DE NACIMIENTO DEL PERFIL
    private Date feNacimientoPerfil;

    // NOMBRE ORIGINAL DE LA IMAGEN
    private String imgPerfil;

    // TIPO DE IMAGEN
    private String tipoImg;

    // NOMBRE DE LA IMAGEN
    private byte[] codImg;

    // DESCRIBIR SOBRE MÍ DE COMO SOY
    private String sobreMi;

    // RESUMEN DE LO QUE HAGO
    private String resumen;

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

    /**--------nuevo------- */
    // Ubigeo nacimiento u origen
    private String descUbigeoNacimientoCompleto;
}
