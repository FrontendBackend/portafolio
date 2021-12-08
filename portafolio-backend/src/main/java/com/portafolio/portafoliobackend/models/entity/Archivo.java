package com.portafolio.portafoliobackend.models.entity;

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
@Table(name = "ARCHIVO")
@Data
@NoArgsConstructor
public class Archivo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARCHIVO_SEQ")
    @SequenceGenerator(name = "ARCHIVO_SEQ", sequenceName = "ARCHIVO_SEQ", allocationSize = 1)
    @Column(name = "ID_ARCHIVO", nullable = false)
    private Integer idArchivo;

    @Column(name = "filename", length = 50)
    private String filename;

    @Column(name = "filetype", length = 15)
    private String filetype;

    @Column(name = "content", length = 5000)
    private byte[] value;

}
