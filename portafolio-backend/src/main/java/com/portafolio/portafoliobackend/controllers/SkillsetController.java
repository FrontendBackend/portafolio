package com.portafolio.portafoliobackend.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.portafolio.portafoliobackend.dtos.TblSkillsetDTO;
import com.portafolio.portafoliobackend.models.entity.TblSkillset;
import com.portafolio.portafoliobackend.services.SkillsetService;
import com.portafolio.portafoliobackend.services.UploadFileService;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/skillsets")
public class SkillsetController {

	@Autowired
	private SkillsetService skillsetService;

	@Autowired
	private UploadFileService uploadService;

	@GetMapping("/listarSkillset")
	public ResponseEntity<List<TblSkillsetDTO>> listarSkillset() {

		List<TblSkillsetDTO> lTblSkillsetDTO = this.skillsetService.listarSkillset();
		return new ResponseEntity<List<TblSkillsetDTO>>(lTblSkillsetDTO, HttpStatus.OK);
	}

	@PostMapping("/crearSkillset")
	public ResponseEntity<?> crearSkillset(@Valid @RequestBody TblSkillsetDTO tblSkillsetDTO,
			BindingResult resultadoValidacion) throws Exception {

		TblSkillsetDTO tblSkillsetDTOCreado = null;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias para crear un skillset");
			respuesta.put("error", errores.toString());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}

		try {
			tblSkillsetDTOCreado = this.skillsetService.crearSkillset(tblSkillsetDTO);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar crear un skillset");
			respuesta.put("error", e.getMostSpecificCause().getMessage());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "La skillset ha sido creada");
		respuesta.put("tblSkillsetDTOCreado", tblSkillsetDTOCreado);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}

	@GetMapping("/obtenerConfiguracionesGenerales/{idSkillset}")
	public ResponseEntity<?> obtenerConfiguracionesGenerales(@PathVariable Long idSkillset) {
		String mensaje;
		Map<String, Object> respuesta = new HashMap<>();

		TblSkillsetDTO tblSkillsetDTO = null;

		try {
			tblSkillsetDTO = this.skillsetService.obtenerSkillsetsPorId(idSkillset);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la skillset");
			respuesta.put("error", e.getMostSpecificCause().getMessage());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (tblSkillsetDTO == null) {
			mensaje = String.format("La skillset con el id: %d no existe en la base de datos", idSkillset);
			respuesta.put("mensaje", mensaje);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
		}

		respuesta.put("mensaje", "La skillset ha sido recuperado");
		respuesta.put("tblSkillsetDTO", tblSkillsetDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	@PutMapping("/modificarSkillset")
	public ResponseEntity<?> modificarSkillset(@Valid @RequestBody TblSkillsetDTO tblSkillsetDTO,
			BindingResult resultadoValidacion) throws Exception {

		TblSkillset tblSkillsetActual = null;
		TblSkillsetDTO tblSkillsetDTOModificada = null;
		String mensaje;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar La skillset");
			respuesta.put("error", errores.toString());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}

		try {
			tblSkillsetActual = this.skillsetService.findById(tblSkillsetDTO.getIdSkillset());

			if (tblSkillsetActual == null) {
				mensaje = String.format("La skillset %s que intenta actualizar no existe en la base de datos",
						tblSkillsetDTO.getIdSkillset());
				respuesta.put("mensaje", mensaje);

				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error intentar recuperar La skillset a actualizar");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {
			tblSkillsetDTOModificada = this.skillsetService.modificarSkillset(tblSkillsetDTO, tblSkillsetActual);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar modificar La skillset");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "La skillset ha sido modificada");
		respuesta.put("tblSkillsetDTOModificada", tblSkillsetDTOModificada);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}

	@DeleteMapping("/eliminarSkillset/{idSkillset}")
	public ResponseEntity<?> delete(@PathVariable Long idSkillset) throws Exception {
		Map<String, Object> respuesta = new HashMap<>();
		String mensaje;

		TblSkillset tblSkillsetActual = null;

		try {
			tblSkillsetActual = this.skillsetService.findById(idSkillset);
			
			String nombreFotoAnterior = tblSkillsetActual.getFotoSkillset();

			uploadService.eliminar(nombreFotoAnterior);

			if (tblSkillsetActual == null) {
				mensaje = String.format("La skillset %s que intenta eliminar no existe en la base de datos",
						idSkillset);
				respuesta.put("mensaje", mensaje);

				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
			}

		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error intentar recuperar La skillset a eliminar");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {
			this.skillsetService.eliminarSkillset(tblSkillsetActual);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error intentar eliminar una capacitación");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "ok");

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("idSkillset") Long idSkillset) {
		Map<String, Object> response = new HashMap<>();

		TblSkillset tblSkillset = skillsetService.findById(idSkillset);

		if (!archivo.isEmpty()) {

			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copiar(archivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del cliente");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));

				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String nombreFotoAnterior = tblSkillset.getFotoSkillset();

			uploadService.eliminar(nombreFotoAnterior);

			tblSkillset.setFotoSkillset(nombreArchivo);

			skillsetService.save(tblSkillset);

			response.put("tblSkillset", tblSkillset);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);

		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {

		Resource recurso = null;

		try {
			recurso = uploadService.cargar(nombreFoto);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);

	}
}
