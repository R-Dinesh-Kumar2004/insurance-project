package com.project.entities;

import com.project.enums.Type;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "policies")
public class Policy {

    @Id
    private long id;

    private String name;
    private Type type;

}
