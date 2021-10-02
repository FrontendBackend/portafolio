package com.portafolio.portafoliobackend.controllers;

import java.util.List;

import com.portafolio.portafoliobackend.dtos.TblPortafolioDTO;
import com.portafolio.portafoliobackend.services.PortafolioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/portafolios")
public class PortafolioController {
    
    @Autowired
    private PortafolioService portafolioService;

    @GetMapping("/listarPortafolio")
    public ResponseEntity<List<TblPortafolioDTO>> listarPortafolio() {

        List<TblPortafolioDTO> lLegCentroDTO = this.portafolioService.listarPortafolio();
        return new ResponseEntity<List<TblPortafolioDTO>>(lLegCentroDTO, HttpStatus.OK);
    }
}
