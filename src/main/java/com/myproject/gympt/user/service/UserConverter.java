package com.myproject.gympt.user.service;

import com.myproject.gympt.Converter;
import com.myproject.gympt.user.db.UserEntity;
import com.myproject.gympt.user.db.UserRepository;
import com.myproject.gympt.user.model.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserConverter implements Converter<UserDTO, UserEntity> {
    private final UserRepository userRepository;

    @Override
    public UserDTO toDto(UserEntity userEntity) {

        return UserDTO.builder()
                .id(userEntity.getId())
                .uid(userEntity.getUid())
                .password(userEntity.getPassword())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .nickName(userEntity.getNickName())
                .simpleAddress(userEntity.getSimpleAddress())
                .createdAt(userEntity.getCreatedAt())
                .gptCount(userEntity.getGptCount())
                .build();
    }

    @Override
    public UserEntity toEntity(UserDTO userDTO) {
        Optional<UserEntity> byUid = userRepository.findByUid(userDTO.getUid());




        return UserEntity.builder()
                .id(byUid.get().getId())
                .name(userDTO.getName())
                .uid(userDTO.getUid())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail())
                .nickName(userDTO.getNickName())
                .simpleAddress(userDTO.getSimpleAddress())
                .createdAt(byUid.get().getCreatedAt())
                .gptCount(userDTO.getGptCount())
                .build();
    }
}
