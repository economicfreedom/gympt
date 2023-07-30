package com.myproject.gympt.gpt.db;

import com.myproject.gympt.gpt.db.GptEntity;
import com.myproject.gympt.user.db.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GPTRepository extends JpaRepository<GptEntity,Long> {
    List<GptEntity> findAllByUser(UserEntity user);
}
