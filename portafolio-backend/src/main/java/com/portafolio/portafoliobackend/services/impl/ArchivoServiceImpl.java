package com.portafolio.portafoliobackend.services.impl;

import java.util.Optional;

import com.portafolio.portafoliobackend.models.entity.Archivo;
import com.portafolio.portafoliobackend.models.repository.ArchivoRepository;
import com.portafolio.portafoliobackend.services.ArchivoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
// @Slf4j
@Transactional(readOnly = true)
public class ArchivoServiceImpl implements ArchivoService{

    @Autowired
    private ArchivoRepository repo;

    @Override
    @Transactional(readOnly = false)
    public int guardar(Archivo archivo) {
        Archivo ar = repo.save(archivo);
        return ar.getIdArchivo() > 0 ? 1 : 0;
    }

    @Override
    public byte[] leerArchivo(Integer idArchivo) {
        Optional<Archivo> op = repo.findById(idArchivo);
        return op.isPresent() ? op.get().getValue(): new byte[0];
    }
    

}
