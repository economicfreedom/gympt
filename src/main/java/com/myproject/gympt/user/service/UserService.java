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
    public UserEntity getUser(String uid) throws DataFormatException {
        Optional<UserEntity> byUid = userRepository.findByUid(uid);
        if (byUid.isPresent()){
            return byUid.get();
        }else {
            throw new DataFormatException("존재하지 않는 유저입니다");
        }
    }




}
