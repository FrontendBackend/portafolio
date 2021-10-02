package com.portafolio.portafoliobackend.models.repository;

import java.util.List;

import com.portafolio.portafoliobackend.dtos.TblPortafolioDTO;
import com.portafolio.portafoliobackend.models.entity.TblPortafolio;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PortafolioRepository extends JpaRepository<TblPortafolio, Long> {

    public List<TblPortafolioDTO> listarPortafolio();
}
