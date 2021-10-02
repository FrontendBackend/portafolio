package com.portafolio.portafoliobackend.services.impl;

import java.util.List;

import com.portafolio.portafoliobackend.dtos.TblPortafolioDTO;
import com.portafolio.portafoliobackend.models.repository.PortafolioRepository;
import com.portafolio.portafoliobackend.services.PortafolioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
// @Slf4j
@Transactional(readOnly = true)
public class PortafolioServiceImpl implements PortafolioService {

    @Autowired
    private PortafolioRepository portafolioRepository;

    @Override
    public List<TblPortafolioDTO> listarPortafolio() {
        return this.portafolioRepository.listarPortafolio();
    }
    
}
