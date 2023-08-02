package com.myproject.gympt;

import com.myproject.gympt.board.db.BoardEntity;
import com.myproject.gympt.board.db.BoardRepository;
import com.myproject.gympt.board.model.BoardDTO;
import com.myproject.gympt.board.service.BoardConverter;
import com.myproject.gympt.user.db.UserEntity;
import com.myproject.gympt.user.db.UserRepository;
import com.myproject.gympt.user.model.UserDTO;
import com.myproject.gympt.user.service.UserService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.annotation.Documented;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.zip.DataFormatException;

@SpringBootTest
class GymptApplicationTests {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardConverter boardConverter;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserUpdateCount userUpdateCount;

    @Test
    void contextLoads() {
        System.out.println(1);
    }

    @Test
    void boardCreate() {
        Optional<UserEntity> my = userRepository.findByUid("ADMIN_GYMPT");
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
    void userDelete() {
        userRepository.deleteAll();
    }

    @Test
    void getMaxId() {
        System.out.println("실행됨");
        Long maxId = boardRepository.maxId();
        System.out.println("실행종료");
        System.out.println(maxId);
    }
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test void 패스워드엔코더와패스워드() throws DataFormatException {

        UserDTO dto = userService.getUser("dpfoqm12");

        boolean qwer1234 = passwordEncoder.matches("Qwer1234", dto.getPassword());
        if (qwer1234){
            System.out.println("같음");
        }


    }

    @Test
    void statusUpdate(){
        Optional<UserEntity> user = userRepository.findByUid("dpfoqm12");
        UserEntity userEntity = user.get();
        userEntity.setStatus("Active");
        userRepository.save(userEntity);

    }
    @Test void updateGPTCount(){
        Optional<UserEntity> user = userRepository.findByUid("dpfoqm12");
        UserEntity userEntity = user.get();
        userEntity.setGptCount((byte) 0);
        userRepository.save(userEntity);
    }

    @Test
    void 크롤링테스트(){
        String url = "https://www.youtube.com/results?search_query=%EC%9A%B4%EB%8F%99+%ED%95%A0%EB%96%84+%EB%93%A3%EA%B8%B0+%EC%A2%8B%EC%9D%80+%EC%9D%8C%EC%95%85";



        Document document = null;
        try {
            document = Jsoup.connect(url).get();
            Elements elementsByClass = document.getElementsByClass("yt-simple-endpoint style-scope ytd-video-renderer");

            for (int i = 0; i < elementsByClass.size(); i++) {
                System.out.println(elementsByClass.get(i).text());
                System.out.println(elementsByClass.get(i).attr("href"));

            }

        }catch (Exception e){
            e .printStackTrace();
        }


    }
    @Test
    void 어드민계정생성(){
        UserEntity userEntity = UserEntity.builder()
                .gptCount((byte) 0)
                .status("Active")
                .email("gyuha454@gmail.com")
                .name("최규하")
                .nickName("GYMPT_관리자")
                .simpleAddress("김해")
                .createdAt(LocalDateTime.now())
                .password(passwordEncoder.encode("ASDJKL:ASKLCMZXKML:@#!#@$!"))
                .uid("ADMIN_GYMPT")
                .build();

        userRepository.save(userEntity);
    }
    @Test
    void 모든유저지피티사용횟수0으로초기화(){
        userUpdateCount.userGptCountUpdate();
    }
}
