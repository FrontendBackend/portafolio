package com.portafolio.portafoliobackend.services;

import java.util.List;

import com.portafolio.portafoliobackend.dtos.TblCurriculumVitaeAuxDTO;
import com.portafolio.portafoliobackend.dtos.TblEducacionDTO;
import com.portafolio.portafoliobackend.dtos.TblPerfilDTO;
import com.portafolio.portafoliobackend.models.entity.TblPerfil;

import org.springframework.web.multipart.MultipartFile;

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

      // LISTAR PERFILES
      List<TblPerfilDTO> ListarPerfil();



      // PERMITE GENERAR EL CURRICULUM VITAE
      byte[] generarReporteCurriculum(Long idPerfil);

      // ME PERMITE OBTENER LAS PROPIEDADES POR EL ID DE EDUCACIÓN
      TblEducacionDTO obtenerEducacionPorId(Long idPerfil);

      // PERMITE MODIFICAR LA EDUCACIÓN
      public Long modificarEducacion(TblPerfilDTO tblPerfilDTO);

      // PERMITE MODIFICAR LA FORMACIÓN
      public Long modificarFormacion(TblPerfilDTO tblPerfilDTO);

      // PERMITE MODIFICAR LA EXPERIENCIA
      public Long modificarExperiencia(TblPerfilDTO tblPerfilDTO);

      TblPerfil save(TblPerfil tblPerfil);

      /**
       * Correo simple
       * 
       * @param sendto
       * @param title
       * @param content
       */
      void Sender(String sendto, String title, String content);

      /**
       * Enviar correo electrónico con archivo adjunto
       * 
       * @param sendto
       * @param title
       * @param content
       *                archivo de archivo @param
       */
      void sendAttachementFileMail(String[] sendto, String[] cc, String title, String content, MultipartFile[] file);
}
