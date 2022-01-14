package com.portafolio.portafoliobackend.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TBL_UBIGEO")
@Data
@NoArgsConstructor
public class TblUbigeo implements Serializable {

    /*
     * Identificador interno del Ubigeo. Secuencia: TBL_UBIGEO_SEQ
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TBL_UBIGEO_SEQ")
    @SequenceGenerator(name = "TBL_UBIGEO_SEQ", sequenceName = "TBL_UBIGEO_SEQ", allocationSize = 1)
    @Column(name = "ID_UBIGEO", nullable = false)
    private Long idUbigeo;

    /*
     * Codigo unico de ubigeo
     */
    @Column(name = "CODIGO_UNICO", nullable = true, length = 6)
    private String codigoUnico;

    /*
     * Nombre del departamento
     */
    @Column(name = "DEPARTAMENTO", nullable = true, length = 100)
    private String departamento;

    /*
     * Nombre del provincia
     */
    @Column(name = "PROVINCIA", nullable = true, length = 100)
    private String provincia;

    /*
     * Nombre del distrito
     */
    @Column(name = "DISTRITO", nullable = true, length = 100)
    private String distrito;
}
