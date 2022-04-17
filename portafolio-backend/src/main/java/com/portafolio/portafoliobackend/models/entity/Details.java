package com.portafolio.portafoliobackend.models.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

// @Entity
// @Table(name = "DETAILS")
@Data
@NoArgsConstructor
public class Details {
    
    private String name;

    private int age;

    private String country;

    private String email;
}
