package com.portafolio.portafoliobackend.services;

import java.util.List;

import com.portafolio.portafoliobackend.dtos.TblUbigeoDTO;

public interface UbigeoService {

    /***
     * Permite listar ubigeos por el nombre clave.
     * 
     * @param codigoUnico Palabra clave utilizada para buscar en la lista de ubigeos
     * @return
     */
    List<TblUbigeoDTO> listarPorUbigeo(String codigoUnico);
}
