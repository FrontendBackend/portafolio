package com.portafolio.portafoliobackend.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.portafolio.portafoliobackend.dtos.TblPortafolioDTO;
import com.portafolio.portafoliobackend.services.PortafolioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

        List<TblPortafolioDTO> lTblPortafolioDTO = this.portafolioService.listarPortafolio();
        return new ResponseEntity<List<TblPortafolioDTO>>(lTblPortafolioDTO, HttpStatus.OK);
    }

    @PostMapping("/crearPortafolio")
    public ResponseEntity<?> crearPortafolio(@Valid @RequestBody TblPortafolioDTO tblPortafolioDTO,
            BindingResult resultadoValidacion) throws Exception {

        TblPortafolioDTO tblPortafolioDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear un portafolio");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            tblPortafolioDTOCreado = this.portafolioService.crearPortafolio(tblPortafolioDTO);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear un portafolio");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El portafolio ha sido creada");
        respuesta.put("tblPortafolioDTOCreado", tblPortafolioDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }
}
