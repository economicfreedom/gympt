package com.myproject.gympt.cloud.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EconomyCloudRepository extends JpaRepository<EconomyCloudEntity,Integer> {
//public interface EconomyCloudRepository extends CloudInter<EconomyCloudEntity> {
    Optional<EconomyCloudEntity> findFirstByOrderByIdDesc();
}
