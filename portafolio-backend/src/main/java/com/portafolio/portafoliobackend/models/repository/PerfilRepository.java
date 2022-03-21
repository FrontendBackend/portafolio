package com.portafolio.portafoliobackend.models.repository;

import java.util.List;

import com.portafolio.portafoliobackend.dtos.TblCurriculumVitaeAuxDTO;
import com.portafolio.portafoliobackend.dtos.TblPerfilDTO;
import com.portafolio.portafoliobackend.models.entity.TblPerfil;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PerfilRepository extends JpaRepository<TblPerfil, Long>{
    
    @Query("SELECT new com.portafolio.portafoliobackend.dtos.TblPerfilDTOResultado("
            + "per.idPerfil, per.nuDniPerfil, per.noPerfil, per.apPerfil, per.dirPerfil, per.telPerfil, " 
            + "per.emailPerfil, per.feNacimientoPerfil, per.imgPerfil, per.tipoImg, per.codImg, per.sobreMi, per.resumen, " 
            + "ubi.idUbigeo, " 
            + "CASE " 
            + "WHEN ubi.idUbigeo is null THEN TRIM(ubi.departamento || ' ' || ubi.provincia || ' ' || ubi.distrito) " 
            + "ELSE TRIM(ubi.departamento || ', ' || ubi.provincia || ', ' || ubi.distrito) END, " 
            + "per.deEducacion, per.deExperiencia, per.deFormacion, per.deIdioma, per.deHabilidad, per.deDato " 
            + " ) " 
            + "FROM TblPerfil per "
            + "LEFT OUTER JOIN per.tblUbigeo ubi "
            + "WHERE per.idPerfil = :idPerfil " 
            )
    TblPerfilDTO obtenerPerfilPorId(@Param("idPerfil") Long idPerfil);

    @Query("SELECT new com.portafolio.portafoliobackend.dtos.TblCurriculumVitaeAuxDTOResultado("
            + "aux.idPerfil, aux.noPersona, aux.nuDniPerfil, aux.telPerfil, aux.emailPerfil, aux.dirPerfil, " 
            + "aux.noPortafolio, aux.noSkillset, aux.turno, aux.sobreMi, " 
            + "aux.resumen, aux.deEducacion, aux.deExperiencia, aux.deFormacion, " 
            + "aux.deIdioma, aux.deHabilidad, aux.deDato " 
            + " ) " 
            + "FROM TblCurriculumVitaeAux aux "
            + "WHERE aux.idPerfil = :idPerfil "
            )
    List<TblCurriculumVitaeAuxDTO> obtenerCurriculumPorId(@Param("idPerfil") Long idPerfil);
}
