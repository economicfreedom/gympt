package com.myproject.gympt;

import com.myproject.gympt.board.db.BoardEntity;
import com.myproject.gympt.board.db.BoardRepository;
import com.myproject.gympt.user.db.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class GymptApplicationTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void boardCreate() {

        for (int i = 0; i < 300; i++) {
            BoardEntity.builder()
                    .createdAt(LocalDateTime.now())
                    .boardType("문의")
                    .content("테스트" + i)
                    .user(new UserEntity())
                    .title("테스트" + i)
                    .
                    .build();

        }


        boardRepository.save()

    }

}
