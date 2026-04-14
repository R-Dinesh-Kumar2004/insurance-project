package com.project.repositories;

import com.project.entities.UserPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPolicyRepository extends JpaRepository<UserPolicy,Long> {
}
