package com.myproject.gympt.user.service;

import com.myproject.gympt.user.db.UserEntity;
import com.myproject.gympt.user.db.UserRepository;
import com.myproject.gympt.user.model.UserDTO;
import com.myproject.gympt.user.model.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserConverter userConverter;


    public UserDTO create(UserRequest userRequest){
        UserEntity user = UserEntity.builder()
                .uid(userRequest.getUid())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .name(userRequest.getName())
                .nickName(userRequest.getNickName())
                .simpleAddress(userRequest.getSimpleAddr())
                .createdAt(LocalDateTime.now())
                .email(userRequest.getEmail())
                .build();

        UserDTO dto = userConverter.toDto(user);
        userRepository.save(user);

        return dto;
    }
    public UserDTO getUser(String uid) throws DataFormatException {
        Optional<UserEntity> byUid = userRepository.findByUid(uid);

        return getUserDTO(byUid);
    }




    public boolean haEmail(String email){
        Optional<UserEntity> byEmail = userRepository.findByEmail(email);
        return byEmail.isEmpty();
    }

    public UserDTO getNickName(String nick){
        Optional<UserEntity> byNickName = userRepository.findByNickName(nick);
        return getUserDTO(byNickName);

    }
    private UserDTO getUserDTO(Optional<UserEntity> keyWord) {
        if (keyWord.isPresent()){
            return userConverter.toDto(keyWord.get());
        }else {
            return null;
        }
    }

}
