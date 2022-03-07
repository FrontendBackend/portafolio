package com.portafolio.portafoliobackend.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TBL_EXPERIENCIA")
@Data
@NoArgsConstructor
public class TblExperiencia implements Serializable {

    // CAMPO IDENTIFICADOR DE EXPERIENCIA
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TBL_EXPERIENCIA_SEQ")
    @SequenceGenerator(name = "TBL_EXPERIENCIA_SEQ", sequenceName = "TBL_EXPERIENCIA_SEQ", allocationSize = 1)
    @Column(name = "ID_EXPERIENCIA", nullable = false)
    private Long idExperiencia;

    // IDENTIFICADOR INTERNO DEL PERFIL
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PERFIL", nullable = true)
    private TblPerfil tblPerfil;

    // DESCRIBIR TU EXPERIENCIA
    @Lob
    @Column(name = "DE_EXPERIENCIA", nullable = true, length = 8000)
    private String deExperiencia;
}
