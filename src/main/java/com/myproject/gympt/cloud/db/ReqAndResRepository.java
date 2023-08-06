package com.myproject.gympt.cloud.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReqAndResRepository extends JpaRepository<RequestAndResponseEntity,Integer> {
    Optional<RequestAndResponseEntity> findByCloudIdAndNewsType(Integer cloudId, String newsType);


    @Query("SELECT r FROM RequestAndResponseEntity r WHERE r.newsType = :newsType AND r.cloudId = (SELECT MAX(rr.cloudId) FROM RequestAndResponseEntity rr WHERE rr.newsType = r.newsType)")
    Optional<RequestAndResponseEntity> findByNewsTypeAndMaxCloudId(String newsType);
}
