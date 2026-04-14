package com.project.entities;

import com.project.enums.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "policies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Policy {

    @Id
    private long id;

    @Column(length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    private Type type;

    private double premium;
    private double coverageAmount;
    private  int waitingPeriod;
    private String benifits;
    private double claimLimit;

    @OneToMany(mappedBy = "policy")
    private List<UserPolicy> userPolicies;
}
