package com.portafolio.portafoliobackend.services;

import java.util.List;

import com.portafolio.portafoliobackend.dtos.TblPortafolioDTO;

public interface PortafolioService {
    
    // PERMITE LISTAR LOS PORTAFOLIOS
    List<TblPortafolioDTO> listarPortafolio();

    // PERMITE CREAR UN NUEVO PORTAFOLIO
    TblPortafolioDTO crearPortafolio(TblPortafolioDTO tblPortafolioDTO) throws Exception;

    // PERMITE OBTENER LOS DATOS DEL OBJETO
    TblPortafolioDTO obtenerPortafolioPorId(Long idPortafolio);
}
