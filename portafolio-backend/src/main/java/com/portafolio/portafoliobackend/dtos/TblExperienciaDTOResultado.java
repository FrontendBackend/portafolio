package com.portafolio.portafoliobackend.dtos;

public class TblExperienciaDTOResultado extends TblExperienciaDTO {

     /**
     * @see com.portafolio.portafoliobackend.models.repository.ExperienciaRepository#obtenerExperienciaPorId(Long)
     */
    public TblExperienciaDTOResultado(Long idExperiencia, Long idPerfil, String deExperiencia) {
        super();
        this.setIdExperiencia(idExperiencia);
        this.setIdPerfil(idPerfil);
        this.setDeExperiencia(deExperiencia);
    }

}
