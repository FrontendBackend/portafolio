package com.portafolio.portafoliobackend.services;

import java.util.List;

import com.portafolio.portafoliobackend.dtos.TblSkillsetDTO;
import com.portafolio.portafoliobackend.models.entity.TblSkillset;
public interface SkillsetService {
    
    // PERMITE LISTAR LOS SKILLSETS
    List<TblSkillsetDTO> listarSkillset();

    // PERMITE OBTENER LOS DATOS DEL OBJETO
    TblSkillsetDTO obtenerSkillsetsPorId(Long idSkillset);

    // PERMITE CREAR UN NUEVO SKILLSET
    public TblSkillsetDTO crearSkillset(TblSkillsetDTO tblSkillsetDTO) throws Exception;

    // PERMITE MODIFICAR UNA CAPACITACIÓN
    public TblSkillsetDTO modificarSkillset(TblSkillsetDTO tblSkillsetDTO, TblSkillset tblSkillset) throws Exception;

    // PERMITE ELIMINAR EL SKILLSET
    public void eliminarSkillset(TblSkillset tblSkillsetActual) throws Exception;
    
    // PERMITE OBTENER LAS PROPIEDADES DE LOS SKILLSET
    public TblSkillset findById(Long idSkillset);
	
    // PERMITE GUARDAR LA IMAGEN NI BIEN SE< ACTUALIZADO
	public TblSkillset save(TblSkillset tblSkillset);
}
