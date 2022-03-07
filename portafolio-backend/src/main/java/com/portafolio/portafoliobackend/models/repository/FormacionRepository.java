package com.portafolio.portafoliobackend.models.repository;

import com.portafolio.portafoliobackend.dtos.TblFormacionDTO;
import com.portafolio.portafoliobackend.models.entity.TblFormacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FormacionRepository extends JpaRepository<TblFormacion, Long> {

    @Query("SELECT new com.portafolio.portafoliobackend.dtos.TblFormacionDTOResultado("
            + "for.idFormacion, for.tblPerfil.idPerfil, for.deFormacion "
            + " ) "
            + "FROM TblFormacion for "
            + "WHERE for.idFormacion = :idFormacion ")
    TblFormacionDTO obtenerFormacionPorId(@Param("idFormacion") Long idFormacion);
}
