package com.myproject.gympt.cloud.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//public interface PoliticsCloudRepository extends CloudInter<PoliticsCloudEntity>{
public interface PoliticsCloudRepository extends JpaRepository<PoliticsCloudEntity,Integer> {
    Optional<PoliticsCloudEntity> findFirstByOrderByIdDesc();
}
