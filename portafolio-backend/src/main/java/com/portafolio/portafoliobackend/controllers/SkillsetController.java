package com.portafolio.portafoliobackend.controllers;

import java.io.IOException;

import com.portafolio.portafoliobackend.models.entity.TblSkillset;
import com.portafolio.portafoliobackend.services.SkillsetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/skillsets")
public class SkillsetController {
    
    @Autowired
    private SkillsetService skillsetService;

    @PostMapping(value = "/crearSkillset", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Long> crearSkillset(@RequestParam("adjunto") MultipartFile file) throws IOException {
		//@RequestPart("medico") Medico medico
		
		long rpta = 0;
		
		TblSkillset ar = new TblSkillset();
		ar.setFiletype(file.getContentType());
		ar.setFilename(file.getOriginalFilename());
		ar.setValue(file.getBytes());
		
		rpta = skillsetService.crearSkillset(ar);

		return new ResponseEntity<Long>(rpta, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listarSkillset/{idSkillset}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> listarSkillset(@PathVariable("idSkillset") Long idSkillset) throws IOException {
				
		byte[] arr = skillsetService.listarSkillset(idSkillset); 

		return new ResponseEntity<byte[]>(arr, HttpStatus.OK);
	}
}
