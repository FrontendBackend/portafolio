package com.portafolio.portafoliobackend.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "TBL_SKILLSET")
public class TblSkillset {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TBL_SKILLSET_SEQ")
    @SequenceGenerator(name = "TBL_SKILLSET_SEQ", sequenceName = "TBL_SKILLSET_SEQ", allocationSize = 1)
    @Column(name = "ID_SKILLSET", nullable = false)
	private Long idSkillset;

	@Column(name = "filename", length = 50)
	private String filename;

	@Column(name = "filetype", length = 15)
	private String filetype;

	@Column(name = "content")
	private byte[] value;
}
