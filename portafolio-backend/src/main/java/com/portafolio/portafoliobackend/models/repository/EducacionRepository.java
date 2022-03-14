package com.portafolio.portafoliobackend.models.repository;

import com.portafolio.portafoliobackend.dtos.TblEducacionDTO;
import com.portafolio.portafoliobackend.models.entity.TblEducacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EducacionRepository extends JpaRepository<TblEducacion, Long> {
    
    @Query("SELECT new com.portafolio.portafoliobackend.dtos.TblEducacionDTOResultado("
            + "edu.idEducacion, edu.tblPerfil.idPerfil, edu.deEducacion "
            + " ) "
            + "FROM TblEducacion edu "
            + "WHERE edu.tblPerfil.idPerfil = :idPerfil ")
    TblEducacionDTO obtenerEducacionPorId(@Param("idPerfil") Long idPerfil);
}
