package com.portafolio.portafoliobackend.services;

import java.util.List;

import com.portafolio.portafoliobackend.dtos.TblPortafolioDTO;
import com.portafolio.portafoliobackend.models.entity.TblPortafolio;

public interface PortafolioService {
    
    // PERMITE LISTAR LOS PORTAFOLIOS
    List<TblPortafolioDTO> listarPortafolio();

    // PERMITE CREAR UN NUEVO PORTAFOLIO
    TblPortafolioDTO crearPortafolio(TblPortafolioDTO tblPortafolioDTO) throws Exception;

    // PERMITE OBTENER LOS DATOS DEL OBJETO
    TblPortafolioDTO obtenerPortafolioPorId(Long idPortafolio);

    // PERMITE MODIFICAR UN PORTAFOLIO
    public TblPortafolioDTO modificarPortafolio(TblPortafolioDTO tblPortafolioDTO, TblPortafolio tblPortafolio) throws Exception;

    // PERMITE ELIMINAR EL PORTAFOLIO
    public void eliminarPortafolio(TblPortafolio tblPortafolioActual) throws Exception;
    
    // PERMITE OBTENER LAS PROPIEDADES DE LOS PORTAFOLIOS
    public TblPortafolio findById(Long idPortafolio);
}
