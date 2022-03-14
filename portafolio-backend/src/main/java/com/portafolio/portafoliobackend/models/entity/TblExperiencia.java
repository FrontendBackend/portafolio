package com.portafolio.portafoliobackend.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TBL_EXPERIENCIA")
@Data
@NoArgsConstructor
public class TblExperiencia implements Serializable {

    // CAMPO IDENTIFICADOR DE EXPERIENCIA
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TBL_EXPERIENCIA_SEQ")
    @SequenceGenerator(name = "TBL_EXPERIENCIA_SEQ", sequenceName = "TBL_EXPERIENCIA_SEQ", allocationSize = 1)
    @Column(name = "ID_EXPERIENCIA", nullable = false)
    private Long idExperiencia;

    // IDENTIFICADOR INTERNO DEL PERFIL
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PERFIL", nullable = true)
    private TblPerfil tblPerfil;

    // DESCRIBIR TU EXPERIENCIA
    @Lob
    @Column(name = "DE_EXPERIENCIA", nullable = true, length = 8000)
    private String deExperiencia;

    /**
     * ---------------------------------
     * AUDITORIA---------------------------------------
     */

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
