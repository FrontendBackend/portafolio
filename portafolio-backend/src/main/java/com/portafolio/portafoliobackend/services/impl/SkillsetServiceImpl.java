package com.portafolio.portafoliobackend.services.impl;

import java.util.Optional;

import com.portafolio.portafoliobackend.models.entity.TblSkillset;
import com.portafolio.portafoliobackend.models.repository.SkillsetRepository;
import com.portafolio.portafoliobackend.services.SkillsetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// @Slf4j
@Transactional(readOnly = true)
@Service
public class SkillsetServiceImpl implements SkillsetService {

    @Autowired
	private SkillsetRepository skillsetRepository;
    
    // @Transactional(readOnly = false)
    @Override
    public long crearSkillset(TblSkillset tblSkillset) {
        TblSkillset ar = skillsetRepository.save(tblSkillset);
		return ar.getIdSkillset() > 0 ? 1 : 0;
    }

    @Override
    public byte[] listarSkillset(Long idSkillset) {
        Optional<TblSkillset> op = skillsetRepository.findById(idSkillset);		
		return op.isPresent() ? op.get().getValue() : new byte[0];
    }
    
}
