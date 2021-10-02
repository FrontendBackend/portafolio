package com.portafolio.portafoliobackend.dtos;

public class TblPortafolioDTOResultado extends TblPortafolioDTO {

    public TblPortafolioDTOResultado(Long idPortafolio, String noPortafolio, String dePortafolio,
            String imgPortafolio) {
        super();
        this.setIdPortafolio(idPortafolio);
        this.setNoPortafolio(noPortafolio);
        this.setDePortafolio(dePortafolio);
        this.setImgPortafolio(imgPortafolio);
    }
}
