package com.portafolio.portafoliobackend.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "VW_CURRICULUM_VITAE_AUX")
@Data
@NoArgsConstructor
@Immutable
public class TblCurriculumVitaeAux implements Serializable {

    // CAMPO IDENTIFICADOR DE PERFIL
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VW_CURRICULUM_VITAE_AUX_SEQ")
    @SequenceGenerator(name = "VW_CURRICULUM_VITAE_AUX_SEQ", sequenceName = "VW_CURRICULUM_VITAE_AUX_SEQ", allocationSize = 1)
    @Column(name = "ID_PERFIL", nullable = true)
    private Long idPerfil;

    // CAMPO NOMBRE COMPLETO DE LA PERSONA
    @Column(name = "NO_PERSONA", nullable = true)
    private String noPersona;

    // CAMPO NUMERO DE DNI DEL PERFIL
    @Column(name = "NU_DNI_PERFIL", nullable = true)
    private Long nuDniPerfil;

    // CAMPO TELEFONO DEL PERFIL
    @Column(name = "TEL_PERFIL", nullable = true)
    private Long telPerfil;

    // CAMPO CORREO ELECTRONICO DEL PERFIL
    @Column(name = "EMAIL_PERFIL", nullable = false)
    private String emailPerfil;

    // CAMPO DIRECCIÓN DEL PERFIL
    @Column(name = "DIR_PERFIL", nullable = true)
    private String dirPerfil;

    // CAMPO NOMBRES DE PORTAFOLIOS COMO LISTADO EN UNA FUNCIÓN CREADA EN LA BD
    @Column(name = "NO_PORTAFOLIO", nullable = true)
    private String noPortafolio;

    // CAMPO NOMBRES DEL SKILLSETS COMO LISTADO EN UNA FUNCIÓN CREADA EN LA BD
    @Column(name = "NO_SKILLSET", nullable = true)
    private String noSkillset;

    // CAMPO TURNO DEL DIA = BUENOS DIAS, TARDES Y NOCHES
    @Column(name = "TURNO", nullable = true)
    private String turno;

    // DESCRIBIR SOBRE MÍ DE COMO SOY
    @Column(name = "SOBRE_MI", nullable = true)
    private String sobreMi;

    // RESUMEN DE LO QUE HAGO
    @Column(name = "RESUMEN", nullable = true)
    private String resumen;
}
