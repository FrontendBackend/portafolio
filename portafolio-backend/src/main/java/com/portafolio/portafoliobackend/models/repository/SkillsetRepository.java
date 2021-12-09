package com.portafolio.portafoliobackend.models.repository;

import java.util.List;

import com.portafolio.portafoliobackend.dtos.TblSkillsetDTO;
import com.portafolio.portafoliobackend.models.entity.TblSkillset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SkillsetRepository extends JpaRepository<TblSkillset, Long> {
    
    // PERMITE LISTAR LOS SKILLSETS
    @Query("SELECT new com.portafolio.portafoliobackend.dtos.TblSkillsetDTOResultado("
            + "sk.idSkillset, sk.noSkillset, sk.fotoSkillset, sk.filename, sk.filetype, sk.value " 
            + " ) " 
            + "FROM TblSkillset sk "
            + "WHERE sk.esRegistro = '1' "
            )
    List<TblSkillsetDTO> listarSkillset();

    // PERMITE OBTENER LOS DATOS DEL SKILLSETS
    @Query("SELECT new com.portafolio.portafoliobackend.dtos.TblSkillsetDTOResultado("
            + "sk.idSkillset, sk.noSkillset, sk.fotoSkillset, sk.filename, sk.filetype, sk.value " 
            + " ) " 
            + "FROM TblSkillset sk "
            + "WHERE sk.idSkillset = :idSkillset " 
            )
    TblSkillsetDTO obtenerSkillsetsPorId(@Param("idSkillset") Long idSkillset);
}
