package com.portafolio.portafoliobackend.services;

import com.portafolio.portafoliobackend.models.entity.TblSkillset;

public interface SkillsetService {
    
    long crearSkillset(TblSkillset tblSkillset);

	byte[] listarSkillset(Long idSkillset);
}
