package com.myproject.gympt.user.service;

import com.myproject.gympt.user.db.UserEntity;
import com.myproject.gympt.user.db.UserRepository;
import com.myproject.gympt.user.model.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserEntity create(UserRequest userRequest){
        UserEntity user = UserEntity.builder()
                .uid(userRequest.getUid())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .name(userRequest.getName())
                .nickName(userRequest.getNickName())
                .simpleAddress(userRequest.getSimpleAddr())
                .createdAt(LocalDateTime.now())
                .email(userRequest.getEmail())
                .build();

        userRepository.save(user);

        return user;
    }




}
