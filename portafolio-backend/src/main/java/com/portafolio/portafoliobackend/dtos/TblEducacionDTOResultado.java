package com.portafolio.portafoliobackend.dtos;

public class TblEducacionDTOResultado extends TblEducacionDTO {

    /**
     * @see com.portafolio.portafoliobackend.models.repository.EducacionRepository#obtenerEducaciónPorId(Long)
     */
    public TblEducacionDTOResultado(Long idEducacion, Long idPerfil, String deEducacion) {
        super();
        this.setIdEducacion(idEducacion);
        this.setIdPerfil(idPerfil);
        this.setDeEducacion(deEducacion);
    }

}
