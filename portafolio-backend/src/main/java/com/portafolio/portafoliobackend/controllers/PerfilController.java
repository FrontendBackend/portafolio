package com.portafolio.portafoliobackend.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.portafolio.portafoliobackend.dtos.ResponseDTO;
import com.portafolio.portafoliobackend.dtos.TblEducacionDTO;
import com.portafolio.portafoliobackend.dtos.TblExperienciaDTO;
import com.portafolio.portafoliobackend.dtos.TblFormacionDTO;
import com.portafolio.portafoliobackend.dtos.TblPerfilDTO;
import com.portafolio.portafoliobackend.dtos.TblUbigeoDTO;
import com.portafolio.portafoliobackend.models.entity.TblEducacion;
import com.portafolio.portafoliobackend.models.entity.TblPerfil;
import com.portafolio.portafoliobackend.services.PerfilService;
import com.portafolio.portafoliobackend.services.UbigeoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

@RestController
@Slf4j
@RequestMapping("/perfiles")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @Autowired
    private UbigeoService ubigeoService;

    @PostMapping("/crearPerfil")
    public ResponseEntity<?> crearPerfil(@Valid @RequestBody TblPerfilDTO tblPerfilDTO,
            BindingResult resultadoValidacion) throws Exception {

        TblPerfilDTO tblPerfilDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear un perfil");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            tblPerfilDTOCreado = this.perfilService.crearPerfil(tblPerfilDTO);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear un perfil");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El perfil ha sido creada");
        respuesta.put("tblPerfilDTOCreado", tblPerfilDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @GetMapping("/obtenerConfiguracionesGenerales/{idPerfil}")
    public ResponseEntity<?> obtenerConfiguracionesGenerales(@PathVariable Long idPerfil) {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        TblPerfilDTO tblPerfilDTO = null;

        try {
            tblPerfilDTO = this.perfilService.obtenerPerfilPorId(idPerfil);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el perfil");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (tblPerfilDTO == null) {
            mensaje = String.format("El perfil con el id: %d no existe en la base de datos", idPerfil);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "El perfil ha sido recuperado");
        respuesta.put("tblPerfilDTO", tblPerfilDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PutMapping("/modificarPerfil")
    public ResponseEntity<?> modificarPerfil(@Valid @RequestBody TblPerfilDTO tblPerfilDTO,
            BindingResult resultadoValidacion) throws Exception {

        TblPerfil tblPerfilActual = null;
        TblPerfilDTO tblPerfilDTOModificada = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el perfil");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            tblPerfilActual = this.perfilService.findById(tblPerfilDTO.getIdPerfil());

            if (tblPerfilActual == null) {
                mensaje = String.format("el perfil %s que intenta actualizar no existe en la base de datos",
                        tblPerfilDTO.getIdPerfil());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar el perfil a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            tblPerfilDTOModificada = this.perfilService.modificarPerfil(tblPerfilDTO, tblPerfilActual);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar el perfil");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "el perfil ha sido modificada");
        respuesta.put("tblPerfilDTOModificada", tblPerfilDTOModificada);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @GetMapping("/filtrar/palabraClave/{codigoUnico}")
    public ResponseEntity<?> listarPorUbigeo(@PathVariable String codigoUnico) {

        Map<String, Object> respuesta = new HashMap<>();
        List<TblUbigeoDTO> lTblUbigeoDTO = null;

        if (codigoUnico == "_vacio_") {
            lTblUbigeoDTO = new ArrayList<TblUbigeoDTO>();
            respuesta.put("mensaje", "No se encontraron ubigeos");
            respuesta.put("lTblUbigeoDTO", lTblUbigeoDTO);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        try {
            lTblUbigeoDTO = this.ubigeoService.listarPorUbigeo(codigoUnico);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de ubigeos");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron ubigeos");
        respuesta.put("lTblUbigeoDTO", lTblUbigeoDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping(value = "/generarReporteCurriculum/{idPerfil}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> generarReporte(@PathVariable Long idPerfil) {
        byte[] data = null;
        data = perfilService.generarReporteCurriculum(idPerfil);
        return new ResponseEntity<byte[]>(data, HttpStatus.OK);
    }

    @GetMapping("/obtenerEducacionPorId/{idPerfil}")
    public ResponseEntity<?> obtenerEducacionPorId(@PathVariable Long idPerfil) {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        TblEducacionDTO tblEducacionDTO = null;

        try {
            tblEducacionDTO = this.perfilService.obtenerEducacionPorId(idPerfil);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el perfil");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (tblEducacionDTO == null) {
            mensaje = String.format("El perfil con el id: %d no existe en la base de datos", idPerfil);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "El perfil ha sido recuperado");
        respuesta.put("tblEducacionDTO", tblEducacionDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/obtenerFormacionPorId/{idFormacion}")
    public ResponseEntity<?> obtenerFormacionPorId(@PathVariable Long idFormacion) {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        TblFormacionDTO tblFormacionDTO = null;

        try {
            tblFormacionDTO = this.perfilService.obtenerFormacionPorId(idFormacion);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el perfil");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (tblFormacionDTO == null) {
            mensaje = String.format("El perfil con el id: %d no existe en la base de datos", idFormacion);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "El perfil ha sido recuperado");
        respuesta.put("tblFormacionDTO", tblFormacionDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/obtenerExperienciaPorId/{idEducacion}")
    public ResponseEntity<?> obtenerExperienciaPorId(@PathVariable Long idExperiencia) {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        TblExperienciaDTO tblExperienciaDTO = null;

        try {
            tblExperienciaDTO = this.perfilService.obtenerExperienciaPorId(idExperiencia);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la experiencia");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (tblExperienciaDTO == null) {
            mensaje = String.format("La experiencia con el id: %d no existe en la base de datos", idExperiencia);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "La experiencia ha sido recuperado");
        respuesta.put("tblExperienciaDTO", tblExperienciaDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PutMapping("/modificarEducacion")
    public ResponseEntity<?> modificarEducacion(@Valid @RequestBody TblEducacionDTO tblEducacionDTO,
            BindingResult resultadoValidacion) throws Exception {

        TblEducacion tblEducacionActual = null;
        TblEducacionDTO tblEducacionDTOModificada = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el perfil");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            tblEducacionActual = this.perfilService.findByEducacionId(tblEducacionDTO.getIdEducacion());

            if (tblEducacionActual == null) {
                mensaje = String.format("el perfil %s que intenta actualizar no existe en la base de datos",
                        tblEducacionDTO.getIdEducacion());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar el perfil a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            tblEducacionDTOModificada = this.perfilService.modificarEducacion(tblEducacionDTO, tblEducacionActual);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar el perfil");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "el perfil ha sido modificada");
        respuesta.put("tblEducacionDTOModificada", tblEducacionDTOModificada);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @PostMapping("modificarEducacion2")
    public ResponseEntity<ResponseDTO> modificarEducacion2(@RequestBody TblEducacionDTO tblEducacionDTO)
            throws Exception {

        Long rpta = perfilService.modificarEducacion2(tblEducacionDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDTO("success", "La observación de acta de inicio a sido actualizada correctamente", rpta));
    }
}
