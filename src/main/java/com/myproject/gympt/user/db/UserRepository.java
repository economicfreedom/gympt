package com.myproject.gympt.user.db;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByUid(String uid);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByNickName(String nickName);

    Optional<UserEntity> findByUidAndEmail(String uid,String email);
}
