package com.portafolio.portafoliobackend.dtos;

public class TblCurriculumVitaeAuxDTOResultado extends TblCurriculumVitaeAuxDTO {

    /**
     * @see com.portafolio.portafoliobackend.models.repository.PerfilRepository#obtenerCurriculumPorId(Long)
     */
    public TblCurriculumVitaeAuxDTOResultado(Long idPerfil, String noPersona, Long nuDniPerfil, Long telPerfil,
            String emailPerfil, String dirPerfil, String noPortafolio, String noSkillset, String turno, String sobreMi,
            String resumen, String deEducacion, String deExperiencia, String deFormacion, String deIdioma,
            String deHabilidad, String deDato) {
        super();
        this.setIdPerfil(idPerfil);
        this.setNoPersona(noPersona);
        this.setNuDniPerfil(nuDniPerfil);
        this.setTelPerfil(telPerfil);
        this.setEmailPerfil(emailPerfil);
        this.setDirPerfil(dirPerfil);
        this.setNoPortafolio(noPortafolio);
        this.setNoSkillset(noSkillset);
        this.setTurno(turno);
        this.setSobreMi(sobreMi);
        this.setResumen(resumen);
        this.setDeEducacion(deEducacion);
        this.setDeExperiencia(deExperiencia);
        this.setDeFormacion(deFormacion);
        this.setDeIdioma(deIdioma);
        this.setDeHabilidad(deHabilidad);
        this.setDeDato(deDato);
    }

}
