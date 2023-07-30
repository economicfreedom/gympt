package com.myproject.gympt.gpt.service;

import com.myproject.gympt.gpt.db.GPTRepository;
import com.myproject.gympt.gpt.db.GptEntity;
import com.myproject.gympt.user.db.UserEntity;
import com.myproject.gympt.user.db.UserRepository;
import com.myproject.gympt.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GPTService {
    private final GPTRepository gptRepository;
    private final UserRepository userRepository;

    public Byte create(String request, String response, Principal principal) {

        UserEntity userEntity = userRepository.findByUid(principal.getName()).get();

        GptEntity gptEntity = GptEntity.builder()
                .createdAt(LocalDateTime.now())
                .request(request)
                .response(response)
                .user(userEntity)
                .build();

        GptEntity save = gptRepository.save(gptEntity);
        if (save == null) {
            return 0;
        }
        return 1;

    }

    public List<GptEntity> list(Principal principal){

        UserEntity userEntity = userRepository.findByUid(principal.getName()).get();

        return gptRepository.findAllByUser(userEntity);

    }
}
