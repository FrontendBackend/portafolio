package com.portafolio.portafoliobackend.controllers;

import java.io.IOException;

import com.portafolio.portafoliobackend.models.entity.Archivo;
import com.portafolio.portafoliobackend.services.ArchivoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/archivos")
public class ArchivoController {

    @Autowired
    private ArchivoService serviceArchivo;
    
    @PostMapping(value = "/guardarArchivo", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Integer> guardarArchivo(@RequestParam("adjunto") MultipartFile file) throws IOException {
        int rpta = 0;

        Archivo ar = new Archivo();

        ar.setFiletype(file.getContentType());
        ar.setFilename(file.getOriginalFilename());
        ar.setValue(file.getBytes());

        rpta = serviceArchivo.guardar(ar);

        return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
    }

    @GetMapping(value = "/leerArchivo/{idArchivo}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> leerArchivo(@PathVariable("idArchivo") Integer idArchivo) throws IOException {

        byte[] arr = serviceArchivo.leerArchivo(idArchivo);

        return new ResponseEntity<byte[]>(arr, HttpStatus.OK);
    }
}
