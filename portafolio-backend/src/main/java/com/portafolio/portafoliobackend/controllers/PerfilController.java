package com.portafolio.portafoliobackend.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.portafolio.portafoliobackend.dtos.ResponseDTO;
import com.portafolio.portafoliobackend.dtos.TblEducacionDTO;
import com.portafolio.portafoliobackend.dtos.TblPerfilDTO;
import com.portafolio.portafoliobackend.dtos.TblUbigeoDTO;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    PerfilService emailService;

    @GetMapping("/listarPerfil")
    public ResponseEntity<List<TblPerfilDTO>> ListarPerfil() {

        List<TblPerfilDTO> lTblPerfilDTO = this.perfilService.ListarPerfil();
        return new ResponseEntity<List<TblPerfilDTO>>(lTblPerfilDTO, HttpStatus.OK);
    }

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

    @PostMapping("modificarEducacion")
    public ResponseEntity<ResponseDTO> modificarEducacion(@RequestBody TblPerfilDTO tblPerfilDTO)
            throws Exception {

        Long rpta = perfilService.modificarEducacion(tblPerfilDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDTO("success", "La educación ha sido actualizada correctamente", rpta));
    }

    @PostMapping("modificarFormacion")
    public ResponseEntity<ResponseDTO> modificarFormacion(@RequestBody TblPerfilDTO tblPerfilDTO)
            throws Exception {

        Long rpta = perfilService.modificarFormacion(tblPerfilDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDTO("success", "La formación ha sido actualizada correctamente", rpta));
    }

    @PostMapping("modificarExperiencia")
    public ResponseEntity<ResponseDTO> modificarExperiencia(@RequestBody TblPerfilDTO tblPerfilDTO)
            throws Exception {

        Long rpta = perfilService.modificarExperiencia(tblPerfilDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDTO("success", "La experiencia ha sido actualizada correctamente", rpta));
    }

    @PostMapping(value = "/guardarArchivo", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> guardarArchivo(@RequestParam("adjunto") MultipartFile file,
            @RequestParam("idPerfil") Long idPerfil) throws IOException {

        Map<String, Object> response = new HashMap<>();

        TblPerfil tblPerfil = perfilService.findById(idPerfil);

        tblPerfil.setTipoImg(file.getContentType());
        tblPerfil.setImgPerfil(file.getOriginalFilename());
        tblPerfil.setCodImg(file.getBytes());

        perfilService.save(tblPerfil);

        response.put("tblPerfil", tblPerfil);
        response.put("mensaje", "Has subido correctamente la imagen: " + tblPerfil.getImgPerfil());

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/subirArchivosGmail", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> subirArchivosGmail(@RequestParam("a") String[] a, @RequestParam("cc") String[] cc, @RequestParam("titulo") String titulo,
            @RequestParam("contenido") String contenido, @RequestParam("adjunto") MultipartFile[] file)
            throws IOException {

        Map<String, Object> response = new HashMap<>();

        List<String> fileNames = new ArrayList<>();

        try {
            Arrays.asList(file)
                    .stream()
                    .forEach(fil -> {
                        fileNames.add(fil.getOriginalFilename());
                    });
            emailService.sendAttachementFileMail(a, cc, titulo, contenido, file);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }

        response.put("mensaje", "Has subido correctamente el/los archivo(s): " + fileNames);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}
