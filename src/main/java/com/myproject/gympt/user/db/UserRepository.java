package com.myproject.gympt.user.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUid(String uid);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByNickName(String nickName);

    @Transactional
    @Modifying
    @Query("UPDATE UserEntity u SET u.gptCount = CAST(0 AS byte)")
    void resetAllGptCounts();


    Optional<UserEntity> findByUidAndEmail(String uid, String email);
}
