package com.portafolio.portafoliobackend.models.repository;

import com.portafolio.portafoliobackend.models.entity.Archivo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchivoRepository extends JpaRepository<Archivo, Integer>  {
    
}
