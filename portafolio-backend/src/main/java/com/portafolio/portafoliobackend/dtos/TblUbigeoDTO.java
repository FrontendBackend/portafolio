package com.portafolio.portafoliobackend.dtos;

import lombok.Data;

@Data
public class TblUbigeoDTO {

    /*
     * Identificador interno del Ubigeo. Secuencia: TBL_UBIGEO_SEQ
     */
    private Long idUbigeo;

    /*
     * Codigo unico de ubigeo
     */
    private String codigoUnico;

    /*
     * Nombre del departamento
     */
    private String departamento;

    /*
     * Nombre del provincia
     */
    private String provincia;

    /*
     * Nombre del distrito
     */
    private String distrito;
}
