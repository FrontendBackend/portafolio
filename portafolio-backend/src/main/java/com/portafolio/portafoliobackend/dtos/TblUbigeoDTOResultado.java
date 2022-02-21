package com.portafolio.portafoliobackend.dtos;

public class TblUbigeoDTOResultado extends TblUbigeoDTO {

    /**
     * @see com.portafolio.portafoliobackend.models.repository.UbigeoRepository#listarPorUbigeo
     */
    public TblUbigeoDTOResultado(Long idUbigeo, String codigoUnico) {
        super();
        this.setIdUbigeo(idUbigeo);
        this.setCodigoUnico(codigoUnico);
    }
}
