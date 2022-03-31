package com.portafolio.portafoliobackend.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.Immutable;

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
    @Column(name = "ID_PERFIL", updatable = false, nullable = true)
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

    // DESCRIBIR TU EDUCACIÓN
    @Lob
    @Column(name = "DE_EDUCACION", nullable = true)
    private String deEducacion;

    // DESCRIBIR TU EXPERIENCIA
    @Lob
    @Column(name = "DE_EXPERIENCIA", nullable = true)
    private String deExperiencia;

    // DESCRIBIR TU FORMACION
    @Lob
    @Column(name = "DE_FORMACION", nullable = true)
    private String deFormacion;

    // DESCRIBIR LOS IDIOMAS QUE USAS
    @Column(name = "DE_IDIOMA", nullable = true)
    private String deIdioma;

    // DESCRIBIR TUS HABILIDADES
    @Column(name = "DE_HABILIDAD", nullable = true)
    private String deHabilidad;
    
    // DESCRIBIR TUS DATOS
    @Column(name = "DE_DATO", nullable = true)
    private String deDato;
    
    // NOMBRE DE LA IMAGEN
    @Column(name = "COD_IMG", nullable = true)
    private byte[] codImg;
}
