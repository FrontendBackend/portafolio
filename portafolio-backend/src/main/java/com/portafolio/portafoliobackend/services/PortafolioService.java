package com.portafolio.portafoliobackend.services;

import java.util.List;

import com.portafolio.portafoliobackend.dtos.TblPortafolioDTO;

public interface PortafolioService {
    
    // PERMITE LISTAR LOS PORTAFOLIOS
    List<TblPortafolioDTO> listarPortafolio();
}
