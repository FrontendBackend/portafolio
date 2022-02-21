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
@Table(name = "TBL_PORTAFOLIO")
@Data
@NoArgsConstructor
public class TblPortafolio {

    // CAMPO IDENTIFICADOR DEL PORTAFOLIO
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TBL_PORTAFOLIO_SEQ")
    @SequenceGenerator(name = "TBL_PORTAFOLIO_SEQ", sequenceName = "TBL_PORTAFOLIO_SEQ", allocationSize = 1)
    @Column(name = "ID_PORTAFOLIO", nullable = false)
    private Long idPortafolio;

    // IDENTIFICADOR INTERNO DEL PERFIL
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PERFIL", nullable = true)
    private TblPerfil tblPerfil;

    // CAMPO NOMBRE DE PORTAFOLIO
    @Column(name = "NO_PORTAFOLIO", nullable = false, length = 100)
    private String noPortafolio;

    // CAMPO DESCRIPCIÓN DEL PORTAFOLIO
    @Column(name = "DE_PORTAFOLIO", nullable = true, length = 1024)
    private String dePortafolio;

    // CAMPO IMAGEN DEL PORTAFOLIO
    @Column(name = "IMG_PORTAFOLIO", nullable = true, length = 500)
    private String imgPortafolio;

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
