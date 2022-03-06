package com.portafolio.portafoliobackend.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TBL_EDUCACION")
@Data
@NoArgsConstructor
public class TblEducacion {

    // CAMPO IDENTIFICADOR DE EDUCACION
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TBL_EDUCACION_SEQ")
    @SequenceGenerator(name = "TBL_EDUCACION_SEQ", sequenceName = "TBL_EDUCACION_SEQ", allocationSize = 1)
    @Column(name = "ID_EDUCACION", nullable = false)
    private Long idEducacion;

    // IDENTIFICADOR INTERNO DEL PERFIL
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PERFIL", nullable = true)
    private TblPerfil tblPerfil;
}
