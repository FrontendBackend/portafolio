package com.portafolio.portafoliobackend.dtos;

public class TblSkillsetDTOResultado extends TblSkillsetDTO {

    /**
     * @see com.portafolio.portafoliobackend.models.repository.SkillsetRepository#listarSkillset
     * @see com.portafolio.portafoliobackend.models.repository.SkillsetRepository#obtenerSkillsetsPorId
     */
    public TblSkillsetDTOResultado(Long idSkillset, String noSkillset, String fotoSkillset, String filename,
            String filetype, byte[] value) {
        super();
        this.setIdSkillset(idSkillset);
        this.setNoSkillset(noSkillset);
        this.setFotoSkillset(fotoSkillset);
        this.setFilename(filename);
        this.setFiletype(filetype);
        this.setValue(value);
    }
}
