package com.portafolio.portafoliobackend.dtos;

import java.util.Date;

public class TblPerfilDTOResultado extends TblPerfilDTO {

    /**
     * @see com.portafolio.portafoliobackend.models.repository.PerfilRepository#obtenerPerfilPorId
     * @see com.portafolio.portafoliobackend.models.repository.PerfilRepository#listarPerfil
     */
    public TblPerfilDTOResultado(Long idPerfil, Long nuDniPerfil, String noPerfil, String apPerfil, String dirPerfil,
            Long telPerfil, String emailPerfil, Date feNacimientoPerfil, String imgPerfil, String tipoImg,
            byte[] codImg, Long idUbigeo, String descUbigeoNacimientoCompleto) {
        super();
        this.setIdPerfil(idPerfil);
        this.setNuDniPerfil(nuDniPerfil);
        this.setNoPerfil(noPerfil);
        this.setApPerfil(apPerfil);
        this.setDirPerfil(dirPerfil);
        this.setTelPerfil(telPerfil);
        this.setEmailPerfil(emailPerfil);
        this.setFeNacimientoPerfil(feNacimientoPerfil);
        this.setImgPerfil(imgPerfil);
        this.setTipoImg(tipoImg);
        this.setCodImg(codImg);
        this.setIdUbigeo(idUbigeo);
        this.setDescUbigeoNacimientoCompleto(descUbigeoNacimientoCompleto);
    }
}
