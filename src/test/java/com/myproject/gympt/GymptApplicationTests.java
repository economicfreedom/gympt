package com.myproject.gympt;

import com.myproject.gympt.board.db.BoardEntity;
import com.myproject.gympt.board.db.BoardRepository;
import com.myproject.gympt.board.model.BoardDTO;
import com.myproject.gympt.board.service.BoardConverter;
import com.myproject.gympt.user.db.UserEntity;
import com.myproject.gympt.user.db.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
class GymptApplicationTests {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardConverter boardConverter;
    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void boardCreate() {
        Optional<UserEntity> my = userRepository.findByUid("dpfoqm12");
        UserEntity userEntity = my.get();


        for (int i = 1; i <= 100; i++) {


            BoardEntity 테스트데이터 = BoardEntity.builder()
                    .title("테스트" + i)
                    .content("테스트" + i)
                    .createdAt(LocalDateTime.now())
                    .user(userEntity)
                    .boardType("버그제보")
                    .replyList(null)
                    .nickName(userEntity.getNickName())
                    .build();
            boardRepository.save(테스트데이터);
        }
        for (int i = 101; i <= 200; i++) {


            BoardEntity 테스트데이터 = BoardEntity.builder()
                    .title("" + i)
                    .content("테스트" + i)
                    .createdAt(LocalDateTime.now())
                    .user(userEntity)
                    .boardType("건의사항")
                    .replyList(null)
                    .nickName(userEntity.getNickName())
                    .build();
            boardRepository.save(테스트데이터);
        }
        for (int i = 201; i <= 300; i++) {


            BoardEntity 테스트데이터 = BoardEntity.builder()
                    .title("될까??" + i)
                    .content("될까???" + i)
                    .createdAt(LocalDateTime.now())
                    .user(userEntity)
                    .boardType("자유")
                    .replyList(null)
                    .nickName(userEntity.getNickName())
                    .build();
            boardRepository.save(테스트데이터);
        }

    }

    @Test
    void boardDelete() {
        for (int i = 0; i < 300; i++) {
            boardRepository.deleteAll();
        }

    }
    @Test
    void userDelete(){
        userRepository.deleteAll();
    }


}
