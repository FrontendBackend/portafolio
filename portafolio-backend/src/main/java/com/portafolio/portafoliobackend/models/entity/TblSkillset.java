package com.portafolio.portafoliobackend.models.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
@Table(name = "TBL_SKILLSET")
public class TblSkillset {

	// ID DE SKILLSET
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TBL_SKILLSET_SEQ")
    @SequenceGenerator(name = "TBL_SKILLSET_SEQ", sequenceName = "TBL_SKILLSET_SEQ", allocationSize = 1)
    @Column(name = "ID_SKILLSET", nullable = false)
	private Long idSkillset;

	// NOMBRE DE SKILLSET
	@Column(name = "NO_SKILLSET", nullable = false, length = 50)
	private String noSkillset;
	
	// FOTO O IMAGEN DE SKILLSET
	@Column(name = "FOTO_SKILLSET", nullable = true)
	private String fotoSkillset;

    // NOMBRE ORIGINAL DE LA IMAGEN
    @Column(name = "NO_IMAGEN_ORIGINAL", nullable = true, length = 50)
    private String filename;

    // TIPO DE IMAGEN
    @Column(name = "TIPO_IMAGEN", nullable = true, length = 15)
    private String filetype;

    // NOMBRE DE LA IMAGEN
    @Column(name = "NO_IMAGEN", nullable = true, length = 5000)
    private byte[] value;

	// CAMPO ESTADO DEL REGISTRO. LOS POSIBLES VALORES SON: "1" = ACTIVO Y "0" = INACTIVO
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
