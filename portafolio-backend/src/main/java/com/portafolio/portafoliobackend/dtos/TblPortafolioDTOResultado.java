package com.portafolio.portafoliobackend.dtos;

public class TblPortafolioDTOResultado extends TblPortafolioDTO {

    /**
     * @see com.portafolio.portafoliobackend.models.repository.PortafolioRepository#listarPortafolio
     * @see com.portafolio.portafoliobackend.models.repository.PortafolioRepository#obtenerPortafolioPorId
     */
    public TblPortafolioDTOResultado(Long idPortafolio, Long idPerfil, String noPortafolio, String dePortafolio,
            String imgPortafolio) {
        super();
        this.setIdPortafolio(idPortafolio);
        this.setIdPerfil(idPerfil);
        this.setNoPortafolio(noPortafolio);
        this.setDePortafolio(dePortafolio);
        this.setImgPortafolio(imgPortafolio);
    }
}
