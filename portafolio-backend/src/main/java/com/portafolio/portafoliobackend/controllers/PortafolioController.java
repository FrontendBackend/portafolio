package com.portafolio.portafoliobackend.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.portafolio.portafoliobackend.dtos.TblPortafolioDTO;
import com.portafolio.portafoliobackend.models.entity.TblPortafolio;
import com.portafolio.portafoliobackend.services.PortafolioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
            log.error(e.getMostSpecificCause().getMessage());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El portafolio ha sido creada");
        respuesta.put("tblPortafolioDTOCreado", tblPortafolioDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @GetMapping("/obtenerConfiguracionesGenerales/{idPortafolio}")
    public ResponseEntity<?> obtenerConfiguracionesGenerales(@PathVariable Long idPortafolio) {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        TblPortafolioDTO tblPortafolioDTO = null;

        try {
            tblPortafolioDTO = this.portafolioService.obtenerPortafolioPorId(idPortafolio);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el portafolio");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (tblPortafolioDTO == null) {
            mensaje = String.format("El portafolio con el id: %d no existe en la base de datos", idPortafolio);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "El portafolio ha sido recuperado");
        respuesta.put("tblPortafolioDTO", tblPortafolioDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PutMapping("/modificarPortafolio")
	public ResponseEntity<?> modificarPortafolio(@Valid @RequestBody TblPortafolioDTO tblPortafolioDTO,
			BindingResult resultadoValidacion) throws Exception {

		TblPortafolio tblPortafolioActual = null;
		TblPortafolioDTO tblPortafolioDTOModificada = null;
		String mensaje;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el portafolio");
			respuesta.put("error", errores.toString());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}

		try {
			tblPortafolioActual = this.portafolioService.findById(tblPortafolioDTO.getIdPortafolio());

			if (tblPortafolioActual == null) {
				mensaje = String.format("el portafolio %s que intenta actualizar no existe en la base de datos",
						tblPortafolioDTO.getIdPortafolio());
				respuesta.put("mensaje", mensaje);

				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error intentar recuperar el portafolio a actualizar");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {
			tblPortafolioDTOModificada = this.portafolioService.modificarPortafolio(tblPortafolioDTO, tblPortafolioActual);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar modificar el portafolio");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "el portafolio ha sido modificada");
		respuesta.put("tblPortafolioDTOModificada", tblPortafolioDTOModificada);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}

	@DeleteMapping("/eliminarPortafolio/{idPortafolio}")
	public ResponseEntity<?> delete(@PathVariable Long idPortafolio) throws Exception {
		Map<String, Object> respuesta = new HashMap<>();
		String mensaje;

		TblPortafolio tblPortafolioActual = null;

		try {
			tblPortafolioActual = this.portafolioService.findById(idPortafolio);
			
			// String nombreFotoAnterior = tblPortafolioActual.getFotoSkillset();

			// uploadService.eliminar(nombreFotoAnterior);

			if (tblPortafolioActual == null) {
				mensaje = String.format("el portafolio %s que intenta eliminar no existe en la base de datos",
						idPortafolio);
				respuesta.put("mensaje", mensaje);

				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
			}

		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error intentar recuperar el portafolio a eliminar");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {
			this.portafolioService.eliminarPortafolio(tblPortafolioActual);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error intentar eliminar el portafolio");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "ok");

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}
}
