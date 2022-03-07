package com.portafolio.portafoliobackend.models.repository;

import com.portafolio.portafoliobackend.dtos.TblExperienciaDTO;
import com.portafolio.portafoliobackend.models.entity.TblExperiencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExperienciaRepository extends JpaRepository<TblExperiencia, Long> {

    @Query("SELECT new com.portafolio.portafoliobackend.dtos.TblEducacionDTOResultado("
            + "exp.idExperiencia, exp.tblPerfil.idPerfil, exp.deExperiencia "
            + " ) "
            + "FROM TblExperiencia exp "
            + "WHERE exp.idExperiencia = :idExperiencia ")
    TblExperienciaDTO obtenerExperienciaPorId(@Param("idExperiencia") Long idExperiencia);
}
