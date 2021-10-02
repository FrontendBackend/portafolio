package com.portafolio.portafoliobackend.models.repository;

import java.util.List;

import com.portafolio.portafoliobackend.dtos.TblPortafolioDTO;
import com.portafolio.portafoliobackend.models.entity.TblPortafolio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PortafolioRepository extends JpaRepository<TblPortafolio, Long> {

    // PERMITE LISTAR LOS PORTAFOLIOS
    @Query("SELECT new com.portafolio.portafoliobackend.dtos.TblPortafolioDTOResultado("
            + "ptf.idPortafolio, ptf.noPortafolio, ptf.dePortafolio, ptf.imgPortafolio " 
            + " ) " 
            + "FROM TblPortafolio ptf "
            + "WHERE ptf.esRegistro = '1' "
            )
    List<TblPortafolioDTO> listarPortafolio();
}
