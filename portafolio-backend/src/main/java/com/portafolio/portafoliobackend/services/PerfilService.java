package com.portafolio.portafoliobackend.services;

import java.util.List;

import com.portafolio.portafoliobackend.dtos.TblCurriculumVitaeAuxDTO;
import com.portafolio.portafoliobackend.dtos.TblEducacionDTO;
import com.portafolio.portafoliobackend.dtos.TblExperienciaDTO;
import com.portafolio.portafoliobackend.dtos.TblFormacionDTO;
import com.portafolio.portafoliobackend.dtos.TblPerfilDTO;
import com.portafolio.portafoliobackend.models.entity.TblEducacion;
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

     // PERMITE OBTENER LAS PROPIEDADES DE EDUCACIÓN
    public TblEducacion findByEducacionId(Long idEducacion);

    // ME PERMITE OBTENER LAS PROPIEDADES POR EL ID DE EDUCACIÓN
    TblEducacionDTO obtenerEducacionPorId(Long idPerfil);

    // PERMITE MODIFICAR LA EDUCACIÓN
    public TblEducacionDTO modificarEducacion(TblEducacionDTO tblEducacionDTO,
            TblEducacion tblEducacion) throws Exception;

    // PERMITE CREAR UN NUEVO PERFIL
    public TblEducacionDTO crearEducacion(TblEducacionDTO tblEducacionDTO) throws Exception;

    public Long modificarEducacion2(TblPerfilDTO tblPerfilDTO);

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
