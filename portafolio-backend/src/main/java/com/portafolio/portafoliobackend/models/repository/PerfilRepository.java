package com.portafolio.portafoliobackend.models.repository;

import com.portafolio.portafoliobackend.dtos.TblPerfilDTO;
import com.portafolio.portafoliobackend.models.entity.TblPerfil;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PerfilRepository extends JpaRepository<TblPerfil, Long>{
    
    @Query("SELECT new com.portafolio.portafoliobackend.dtos.TblPerfilDTOResultado("
            + "per.idPerfil, per.nuDniPerfil, per.noPerfil, per.apPerfil, per.dirPerfil, per.telPerfil, " 
            + "per.emailPerfil, per.feNacimientoPerfil, per.imgPerfil, per.tipoImg, per.codImg, " 
            + "ubi.idUbigeo, " 
            + "CASE " 
            + "WHEN ubi.idUbigeo is null THEN TRIM(ubi.departamento || ' ' || ubi.provincia || ' ' || ubi.distrito) " 
            + "ELSE TRIM(ubi.departamento || ', ' || ubi.provincia || ', ' || ubi.distrito) END " 
            + " ) " 
            + "FROM TblPerfil per "
            + "LEFT OUTER JOIN per.tblUbigeo ubi "
            + "WHERE per.idPerfil = :idPerfil " 
            )
    TblPerfilDTO obtenerPerfilPorId(@Param("idPerfil") Long idPerfil);
}
