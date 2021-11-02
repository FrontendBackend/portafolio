package com.portafolio.portafoliobackend.dtos;

public class TblSkillsetDTOResultado extends TblSkillsetDTO {

    public TblSkillsetDTOResultado(Long idSkillset, String noSkillset, String fotoSkillset) {
        super();
        this.setIdSkillset(idSkillset);
        this.setNoSkillset(noSkillset);
        this.setFotoSkillset(fotoSkillset);
    }
}
