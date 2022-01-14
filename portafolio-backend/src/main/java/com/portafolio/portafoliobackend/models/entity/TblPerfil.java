package com.portafolio.portafoliobackend.models.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TBL_PERFIL")
@Data
@NoArgsConstructor
public class TblPerfil {

    // CAMPO IDENTIFICADOR DE PERFIL
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TBL_PERFIL_SEQ")
    @SequenceGenerator(name = "TBL_PERFIL_SEQ", sequenceName = "TBL_PERFIL_SEQ", allocationSize = 1)
    @Column(name = "ID_PERFIL", nullable = false)
    private Long idPerfil;

    // CAMPO IDENTIFICADOR DE UBIGEO NACIMIENTO DEL PERFIL
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_UBIGEO", nullable = true)
    private TblUbigeo tblUbigeo;
    
    // CAMPO NUMERO DE DNI DEL PERFIL
    @Column(name = "NU_DNI_PERFIL", nullable = false, length = 8)
    private Long nuDniPerfil;

    // CAMPO NOMBRE DE PERFIL
    @Column(name = "NO_PERFIL", nullable = false, length = 50)
    private String noPerfil;

    // CAMPO APELLIDOS DEL PERFIL
    @Column(name = "AP_PERFIL", nullable = false, length = 50)
    private String apPerfil;

    // CAMPO DIRECCIÓN DEL PERFIL
    @Column(name = "DIR_PERFIL", nullable = true, length = 75)
    private String dirPerfil;

    // CAMPO TELEFONO DEL PERFIL
    @Column(name = "TEL_PERFIL", nullable = true, length = 9)
    private Long telPerfil;

    // CAMPO CORREO ELECTRONICO DEL PERFIL
    @Column(name = "EMAIL_PERFIL", nullable = false, length = 50)
    private String emailPerfil;

    // CAMPO FECHA DE NACIMIENTO DEL PERFIL
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FE_NACIMIENTO_PERFIL", nullable = true)
    private Date feNacimientoPerfil;

    // NOMBRE ORIGINAL DE LA IMAGEN
    @Column(name = "IMG_PERFIL", nullable = true, length = 50)
    private String imgPerfil;

    // TIPO DE IMAGEN
    @Column(name = "TIPO_IMG", nullable = true, length = 15)
    private String tipoImg;

    // NOMBRE DE LA IMAGEN
    @Column(name = "COD_IMG", nullable = true, length = 5000)
    private byte[] codImg;

    // CAMPO ESTADO DEL REGISTRO. LOS POSIBLES VALORES SON: "1" = ACTIVO Y "0" =
    // INACTIVO
    @Column(name = "ES_REGISTRO", nullable = false, length = 1)
    private String esRegistro;

    // CAMPO USUARIO CREADOR
    @Column(name = "US_CREACION", nullable = true, length = 10)
    private String usCreacion;

    // CAMPO TERMINAL DE CREACIÓN
    @Column(name = "IP_CREACION", nullable = true, length = 9)
    private String ipCreacion;

    // CAMPO FECHA Y HORA DE CREACIÓN
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FE_CREACION", nullable = true)
    private Date feCreacion;

    // CAMPO USUARIO MODIFICADOR
    @Column(name = "US_ACTUALIZACION", nullable = true, length = 10)
    private String usActualizacion;

    // CAMPO TERMINAL DE MODIFICACIÓN
    @Column(name = "IP_ACTUALIZACION", nullable = true, length = 9)
    private String ipActualizacion;

    // CAMPO FECHA Y HORA DE MODIFICACIÓN
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FE_ACTUALIZACION", nullable = true)
    private Date feActualizacion;
}
