package com.portafolio.portafoliobackend.services.impl;

import java.util.Date;
import java.util.List;

import com.portafolio.portafoliobackend.dtos.TblSkillsetDTO;
import com.portafolio.portafoliobackend.models.entity.TblSkillset;
import com.portafolio.portafoliobackend.models.repository.SkillsetRepository;
import com.portafolio.portafoliobackend.services.SkillsetService;
import com.portafolio.portafoliobackend.utils.ConstantesUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// @Slf4j
@Transactional(readOnly = true)
@Service
public class SkillsetServiceImpl implements SkillsetService {

    @Autowired
    private SkillsetRepository skillsetRepository;

    @Override
    public List<TblSkillsetDTO> listarSkillset() {
        return this.skillsetRepository.listarSkillset();
    }

    @Override
    public TblSkillsetDTO obtenerSkillsetsPorId(Long idSkillset) {
        return this.skillsetRepository.obtenerSkillsetsPorId(idSkillset);
    }

    @Transactional(readOnly = false)
    @Override
    public TblSkillsetDTO crearSkillset(TblSkillsetDTO tblSkillsetDTO) throws Exception {
        TblSkillset tblSkillset = new TblSkillset();

        tblSkillset.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        tblSkillset.setFeCreacion(new Date());
        tblSkillset.setUsCreacion("jvalerio");
        tblSkillset.setIpCreacion("127.0.0.0");

        tblSkillset.setNoSkillset(tblSkillsetDTO.getNoSkillset());

        TblSkillset tblSkillsetCreado = this.skillsetRepository.save(tblSkillset);

        TblSkillsetDTO tblSkillsetDTOCreado = this.obtenerSkillsetsPorId(tblSkillsetCreado.getIdSkillset());

        return tblSkillsetDTOCreado;
    }

    @Override
    @Transactional(readOnly = false)
    public TblSkillset findById(Long idSkillset) {
        return skillsetRepository.findById(idSkillset).orElse(null);
    }

    @Override
    @Transactional(readOnly = false)
    public TblSkillset save(TblSkillset tblSkillset) {
        return skillsetRepository.save(tblSkillset);
    }

    @Override
    @Transactional(readOnly = false)
    public TblSkillsetDTO modificarSkillset(TblSkillsetDTO tblSkillsetDTO, TblSkillset tblSkillset) throws Exception {

        tblSkillset.setFeActualizacion(new Date());
        tblSkillset.setUsActualizacion("jvalerio");
        tblSkillset.setIpActualizacion("127.0.0.0");

        tblSkillset.setNoSkillset(tblSkillsetDTO.getNoSkillset());

        TblSkillset TblSkillsetModificado = this.skillsetRepository.save(tblSkillset);

        TblSkillsetDTO tblSkillsetDTOModificado = this.obtenerSkillsetsPorId(TblSkillsetModificado.getIdSkillset());

        return tblSkillsetDTOModificado;
    }

    @Override
    @Transactional(readOnly = false)
    public void eliminarSkillset(TblSkillset tblSkillsetActual) throws Exception {
        tblSkillsetActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);

        tblSkillsetActual.setFeActualizacion(new Date());
        tblSkillsetActual.setUsActualizacion("jvalerio");
        tblSkillsetActual.setIpActualizacion("127.0.0.0");

        this.skillsetRepository.save(tblSkillsetActual);
    }
}
