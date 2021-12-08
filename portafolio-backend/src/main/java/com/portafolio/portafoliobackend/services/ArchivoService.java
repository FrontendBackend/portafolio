package com.portafolio.portafoliobackend.services;

import com.portafolio.portafoliobackend.models.entity.Archivo;

public interface ArchivoService {
    
    int guardar(Archivo archivo);
    byte[] leerArchivo(Integer idArchivo);
}
