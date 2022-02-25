package com.portafolio.portafoliobackend.services.impl;

import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.portafolio.portafoliobackend.dtos.TblPerfilDTO;
import com.portafolio.portafoliobackend.models.entity.TblPerfil;
import com.portafolio.portafoliobackend.models.entity.TblUbigeo;
import com.portafolio.portafoliobackend.models.repository.PerfilRepository;
import com.portafolio.portafoliobackend.services.PerfilService;
import com.portafolio.portafoliobackend.utils.ConstantesUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
// @Slf4j
@Transactional(readOnly = true)
public class PerfilServiceImpl implements PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    @Override
    public TblPerfilDTO obtenerPerfilPorId(Long idPerfil) {
        return this.perfilRepository.obtenerPerfilPorId(idPerfil);
    }

    @Transactional(readOnly = false)
    @Override
    public TblPerfilDTO crearPerfil(TblPerfilDTO tblPerfilDTO) throws Exception {
        TblPerfil tblPerfil = new TblPerfil();

        tblPerfil.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        tblPerfil.setFeCreacion(new Date());
        tblPerfil.setUsCreacion("jvalerio");
        tblPerfil.setIpCreacion("127.0.0.0");

        tblPerfil.setNuDniPerfil(tblPerfilDTO.getNuDniPerfil());
        tblPerfil.setNoPerfil(tblPerfilDTO.getNoPerfil().toUpperCase());
        tblPerfil.setApPerfil(tblPerfilDTO.getApPerfil().toUpperCase());
        tblPerfil.setDirPerfil(tblPerfilDTO.getDirPerfil());
        tblPerfil.setTelPerfil(tblPerfilDTO.getTelPerfil());
        tblPerfil.setEmailPerfil(tblPerfilDTO.getEmailPerfil());
        tblPerfil.setFeNacimientoPerfil(tblPerfilDTO.getFeNacimientoPerfil());
        tblPerfil.setImgPerfil(tblPerfilDTO.getImgPerfil());
        tblPerfil.setTipoImg(tblPerfilDTO.getTipoImg());
        tblPerfil.setCodImg(tblPerfilDTO.getCodImg());

        // Lugar donde nació (origen)
        if (tblPerfilDTO.getIdUbigeo() != null) {
            TblUbigeo legUbigeoNacimiento = new TblUbigeo();
            legUbigeoNacimiento.setIdUbigeo(tblPerfilDTO.getIdUbigeo());
            tblPerfil.setTblUbigeo(legUbigeoNacimiento);
        } else {
            tblPerfil.setTblUbigeo(null);
        }

        TblPerfil tblPerfilCreado = this.perfilRepository.save(tblPerfil);

        TblPerfilDTO tblPerfilDTOCreado = this.obtenerPerfilPorId(tblPerfilCreado.getIdPerfil());

        return tblPerfilDTOCreado;
    }

    @Transactional(readOnly = false)
    @Override
    public TblPerfilDTO modificarPerfil(TblPerfilDTO tblPerfilDTO, TblPerfil tblPerfil) throws Exception {

        tblPerfil.setFeActualizacion(new Date());
        tblPerfil.setUsActualizacion("jvalerio");
        tblPerfil.setIpActualizacion("127.0.0.0");

        tblPerfil.setNuDniPerfil(tblPerfilDTO.getNuDniPerfil());
        tblPerfil.setNoPerfil(tblPerfilDTO.getNoPerfil().toUpperCase());
        tblPerfil.setApPerfil(tblPerfilDTO.getApPerfil().toUpperCase());
        tblPerfil.setDirPerfil(tblPerfilDTO.getDirPerfil());
        tblPerfil.setTelPerfil(tblPerfilDTO.getTelPerfil());
        tblPerfil.setEmailPerfil(tblPerfilDTO.getEmailPerfil());
        tblPerfil.setFeNacimientoPerfil(tblPerfilDTO.getFeNacimientoPerfil());
        tblPerfil.setImgPerfil(tblPerfilDTO.getImgPerfil());
        tblPerfil.setTipoImg(tblPerfilDTO.getTipoImg());
        tblPerfil.setCodImg(tblPerfilDTO.getCodImg());

        // Lugar donde nació (origen)
        if (tblPerfilDTO.getIdUbigeo() != null) {
            TblUbigeo legUbigeoNacimiento = new TblUbigeo();
            legUbigeoNacimiento.setIdUbigeo(tblPerfilDTO.getIdUbigeo());
            tblPerfil.setTblUbigeo(legUbigeoNacimiento);
        } else {
            tblPerfil.setTblUbigeo(null);
        }

        TblPerfil tblPerfilModificado = this.perfilRepository.save(tblPerfil);

        TblPerfilDTO tblPerfilDTOModificado = this
                .obtenerPerfilPorId(tblPerfilModificado.getIdPerfil());

        return tblPerfilDTOModificado;
    }

    @Override
    public TblPerfil findById(Long idPerfil) {
        return perfilRepository.findById(idPerfil).orElse(null);
    }

    @Override
    public byte[] generarReporteCurriculum(Long idPerfil) {
        byte[] data = null;

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("idPerfil", "idPerfil");

        try {
            File file = new ClassPathResource("/reports/portafolio.jasper").getFile();
            JasperPrint print = JasperFillManager.fillReport(file.getPath(), 
                    parametros,
                    new JRBeanCollectionDataSource(this.listarPerfil(idPerfil)));
            data = JasperExportManager.exportReportToPdf(print);
            // mitocode jasperreports | excel, pdf, ppt, word, csv
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<TblPerfilDTO> listarPerfil(Long idPerfil) {
        return this.perfilRepository.listarPerfil(idPerfil);
    }

}
