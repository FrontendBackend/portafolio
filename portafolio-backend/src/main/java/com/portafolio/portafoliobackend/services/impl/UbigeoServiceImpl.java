package com.portafolio.portafoliobackend.services.impl;

import java.util.List;

import com.portafolio.portafoliobackend.dtos.TblUbigeoDTO;
import com.portafolio.portafoliobackend.models.repository.UbigeoRepository;
import com.portafolio.portafoliobackend.services.UbigeoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UbigeoServiceImpl implements UbigeoService{

    @Autowired
    private UbigeoRepository ubigeoRepository;
    
    @Override
    public List<TblUbigeoDTO> listarPorUbigeo(String codigoUnico) {
        List<TblUbigeoDTO> lTblUbigeoDTO = this.ubigeoRepository.listarPorUbigeo(codigoUnico);
        return lTblUbigeoDTO;
    }
    
}
