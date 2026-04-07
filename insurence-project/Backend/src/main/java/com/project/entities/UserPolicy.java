package com.project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPolicy {

    @ManyToOne
    private User user;

    @ManyToOne
    private Policy policy;

    private LocalDateTime purchaseDate;
}
