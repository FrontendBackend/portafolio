package com.portafolio.portafoliobackend.models.repository;

import com.portafolio.portafoliobackend.models.entity.TblSkillset;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillsetRepository extends JpaRepository<TblSkillset, Long> {
    
}
