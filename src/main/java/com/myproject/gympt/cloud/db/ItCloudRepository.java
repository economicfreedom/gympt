package com.myproject.gympt.cloud.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItCloudRepository extends JpaRepository<ItCloudEntity,Integer> {
    Optional<ItCloudEntity> findFirstByOrderByIdDesc();
}
