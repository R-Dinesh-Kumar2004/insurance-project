package com.project.repositories;

import com.project.entities.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolicyRepository extends JpaRepository<Policy,Long> {
    Policy findByName(String name);

    List<Policy> findByType(String type);

    List<Policy> findAllByPremiumBetween(double minPremium, double maxPremium);

    List<Policy> findAllByCoverageAmountBetween(double minCoverageAmount, double maxCoverageAmount);
}
