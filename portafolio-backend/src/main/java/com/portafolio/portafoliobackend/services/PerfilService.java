package com.portafolio.portafoliobackend.services;

import java.util.List;

import com.portafolio.portafoliobackend.dtos.TblCurriculumVitaeAuxDTO;
import com.portafolio.portafoliobackend.dtos.TblEducacionDTO;
import com.portafolio.portafoliobackend.dtos.TblExperienciaDTO;
import com.portafolio.portafoliobackend.dtos.TblFormacionDTO;
import com.portafolio.portafoliobackend.dtos.TblPerfilDTO;
import com.portafolio.portafoliobackend.models.entity.TblPerfil;

public interface PerfilService {

    /**
     * ----------------------------PERFIL---------------------------------
     */

    // PERMITE OBTENER LOS DATOS DEL OBJETO
    List<TblCurriculumVitaeAuxDTO> obtenerCurriculumPorId(Long idPerfil);

    // PERMITE OBTENER LOS DATOS DEL OBJETO
    public TblPerfilDTO obtenerPerfilPorId(Long idPerfil);

    // PERMITE CREAR UN NUEVO PERFIL
    public TblPerfilDTO crearPerfil(TblPerfilDTO tblPerfilDTO) throws Exception;

    // PERMITE MODIFICAR UN PERFIL
    public TblPerfilDTO modificarPerfil(TblPerfilDTO tblPerfilDTO, TblPerfil tblPerfil) throws Exception;

    // PERMITE OBTENER LAS PROPIEDADES DEL PERFIL
    public TblPerfil findById(Long idPerfil);

    // PERMITE GENERAR EL CURRICULUM VITAE
    byte[] generarReporteCurriculum(Long idPerfil);

    /**
     * ----------------------------EDUCACIÓN---------------------------------
     */

    // ME PERMITE OBTENER LAS PROPIEDADES POR EL ID DE EDUCACIÓN
    TblEducacionDTO obtenerEducacionPorId(Long idEducacion);

    /**
     * ----------------------------FORMACIÓN---------------------------------
     */

    // ME PERMITE OBTENER LAS PROPIEDADES POR EL ID DE FORMACIÓN
    TblFormacionDTO obtenerFormacionPorId(Long idFormacion);

    /**
     * ----------------------------EXPERIENCIA---------------------------------
     */

    // ME PERMITE OBTENER LAS PROPIEDADES POR EL ID DE EXPERIENCIA
    TblExperienciaDTO obtenerExperienciaPorId(Long idExperiencia);

}
