package com.portafolio.portafoliobackend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TblCurriculumVitaeAuxDTO {

    // CAMPO IDENTIFICADOR DE PERFIL
    private Long idPerfil;

    // CAMPO NOMBRE COMPLETO DE LA PERSONA
    private String noPersona;

    // CAMPO NUMERO DE DNI DEL PERFIL
    private Long nuDniPerfil;

    // CAMPO TELEFONO DEL PERFIL
    private Long telPerfil;

    // CAMPO CORREO ELECTRONICO DEL PERFIL
    private String emailPerfil;

    // CAMPO DIRECCIÓN DEL PERFIL
    private String dirPerfil;

    // CAMPO NOMBRES DE PORTAFOLIOS COMO LISTADO EN UNA FUNCIÓN CREADA EN LA BD
    private String noPortafolio;

    // CAMPO NOMBRES DEL SKILLSETS COMO LISTADO EN UNA FUNCIÓN CREADA EN LA BD
    private String noSkillset;

    // CAMPO TURNO DEL DIA = BUENOS DIAS, TARDES Y NOCHES
    private String turno;

    // DESCRIBIR SOBRE MÍ DE COMO SOY
    private String sobreMi;

    // RESUMEN DE LO QUE HAGO
    private String resumen;

    // DESCRIBIR TU EDUCACIÓN
    private String deEducacion;

    // DESCRIBIR TU EXPERIENCIA
    private String deExperiencia;

    // DESCRIBIR TU FORMACION
    private String deFormacion;

    // DESCRIBIR LOS IDIOMAS QUE USAS
    private String deIdioma;

    // DESCRIBIR TUS HABILIDADES
    private String deHabilidad;

    // DESCRIBIR TUS DATOS
    private String deDato;

    // NOMBRE DE LA IMAGEN
    private byte[] codImg;
}
