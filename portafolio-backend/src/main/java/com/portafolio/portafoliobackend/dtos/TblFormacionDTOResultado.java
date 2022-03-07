package com.portafolio.portafoliobackend.dtos;

public class TblFormacionDTOResultado extends TblFormacionDTO {

    /**
     * @see com.portafolio.portafoliobackend.models.repository.FormacionRepository#obtenerFormacionPorId(Long)
     */
    public TblFormacionDTOResultado(Long idFormacion, Long idPerfil, String deFormacion) {
        super();
        this.setIdFormacion(idFormacion);
        this.setIdPerfil(idPerfil);
        this.setDeFormacion(deFormacion);
    }

}
