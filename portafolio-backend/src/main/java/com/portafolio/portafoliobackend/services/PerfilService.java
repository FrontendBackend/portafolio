package com.portafolio.portafoliobackend.services;

import java.util.List;

import com.portafolio.portafoliobackend.dtos.TblPerfilDTO;
import com.portafolio.portafoliobackend.models.entity.TblPerfil;

public interface PerfilService {

    // PERMITE OBTENER LOS DATOS DEL OBJETO
    List<TblPerfilDTO> listarPerfil(Long idPerfil);

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
}
