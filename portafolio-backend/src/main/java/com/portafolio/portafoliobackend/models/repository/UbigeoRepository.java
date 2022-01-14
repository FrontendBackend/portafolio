package com.portafolio.portafoliobackend.models.repository;

import java.util.List;

import com.portafolio.portafoliobackend.dtos.TblUbigeoDTO;
import com.portafolio.portafoliobackend.models.entity.TblUbigeo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UbigeoRepository extends JpaRepository<TblUbigeo, Long> {
    
    @Query("SELECT new com.portafolio.portafoliobackend.dtos.TblUbigeoDTOResultado("
            + "lu.idUbigeo, (lu.departamento || ' ' || lu.provincia || ' ' || lu.distrito) "
            + " )"
            + "FROM TblUbigeo lu "
            + "WHERE (:codigoUnico IS NULL OR LOWER(lu.codigoUnico) LIKE LOWER(CONCAT('%', :codigoUnico, '%')) "
            + "OR LOWER(lu.departamento) LIKE LOWER(CONCAT('%', :codigoUnico, '%')) "
            + "OR LOWER(lu.provincia) LIKE LOWER(CONCAT('%', :codigoUnico, '%')) "
            + "OR LOWER(lu.distrito) LIKE LOWER(CONCAT('%', :codigoUnico, '%')) "
            + ") "
            + "ORDER BY lu.departamento ")
    List<TblUbigeoDTO> listarPorUbigeo(@Param("codigoUnico") String codigoUnico);
}
