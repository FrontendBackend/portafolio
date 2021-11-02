package com.portafolio.portafoliobackend.services.impl;

import java.util.Date;
import java.util.List;

import com.portafolio.portafoliobackend.dtos.TblPortafolioDTO;
import com.portafolio.portafoliobackend.models.entity.TblPortafolio;
import com.portafolio.portafoliobackend.models.repository.PortafolioRepository;
import com.portafolio.portafoliobackend.services.PortafolioService;
import com.portafolio.portafoliobackend.utils.ConstantesUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
// @Slf4j
@Transactional(readOnly = true)
public class PortafolioServiceImpl implements PortafolioService {

    @Autowired
    private PortafolioRepository portafolioRepository;

    @Override
    public List<TblPortafolioDTO> listarPortafolio() {
        return this.portafolioRepository.listarPortafolio();
    }

    @Override
    public TblPortafolioDTO obtenerPortafolioPorId(Long idPortafolio) {
        return this.portafolioRepository.obtenerPortafolioPorId(idPortafolio);
    }

    @Transactional(readOnly = false)
    @Override
    public TblPortafolioDTO crearPortafolio(TblPortafolioDTO tblPortafolioDTO) throws Exception {
        TblPortafolio tblPortafolio = new TblPortafolio();

        this.configurarValores(tblPortafolioDTO, tblPortafolio);

        tblPortafolio.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        tblPortafolio.setFeCreacion(new Date());
        tblPortafolio.setUsCreacion("jvalerio");
        tblPortafolio.setIpCreacion("127.0.0.0");

        TblPortafolio tblPortafolioCreado = this.portafolioRepository.save(tblPortafolio);

        TblPortafolioDTO tblPortafolioDTOCreado = this.obtenerPortafolioPorId(tblPortafolioCreado.getIdPortafolio());

        return tblPortafolioDTOCreado;
    }

    @Transactional(readOnly = false)
    private TblPortafolio configurarValores(TblPortafolioDTO tblPortafolioDTO, TblPortafolio tblPortafolio) {

        tblPortafolio.setNoPortafolio(tblPortafolioDTO.getNoPortafolio());
        tblPortafolio.setDePortafolio(tblPortafolioDTO.getDePortafolio());
        tblPortafolio.setImgPortafolio(tblPortafolioDTO.getImgPortafolio());
        
        return tblPortafolio;
    }

    @Override
    @Transactional(readOnly = false)
    public TblPortafolioDTO modificarPortafolio(TblPortafolioDTO tblPortafolioDTO, TblPortafolio tblPortafolio)
            throws Exception {

        this.configurarValores(tblPortafolioDTO, tblPortafolio);

        tblPortafolio.setFeActualizacion(new Date());
        tblPortafolio.setUsActualizacion("jvalerio");
        tblPortafolio.setIpActualizacion("127.0.0.0");

        TblPortafolio tblPortafolioModificado = this.portafolioRepository.save(tblPortafolio);

        TblPortafolioDTO tblPortafolioDTOModificado = this
                .obtenerPortafolioPorId(tblPortafolioModificado.getIdPortafolio());

        return tblPortafolioDTOModificado;
    }

    @Override
    @Transactional(readOnly = false)
    public void eliminarPortafolio(TblPortafolio tblPortafolioActual) throws Exception {
        tblPortafolioActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);

        tblPortafolioActual.setFeActualizacion(new Date());
        tblPortafolioActual.setUsActualizacion("jvalerio");
        tblPortafolioActual.setIpActualizacion("127.0.0.0");

        this.portafolioRepository.save(tblPortafolioActual);

    }

    @Override
    public TblPortafolio findById(Long idPortafolio) {
        return portafolioRepository.findById(idPortafolio).orElse(null);
    }

}
