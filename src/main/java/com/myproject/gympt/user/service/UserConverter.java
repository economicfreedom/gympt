package com.myproject.gympt.user.service;

import com.myproject.gympt.Converter;
import com.myproject.gympt.gpt.model.GPTDTO;
import com.myproject.gympt.gpt.service.GTPConverter;
import com.myproject.gympt.user.db.UserEntity;
import com.myproject.gympt.user.db.UserRepository;
import com.myproject.gympt.user.model.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserConverter implements Converter<UserDTO, UserEntity> {
    private final UserRepository userRepository;
    private final GTPConverter converter;
    @Override
    public UserDTO toDto(UserEntity userEntity) {
        List<GPTDTO> gptdtoList = userEntity.getGptEntities()
                .stream()
                .map(converter::toDto)
                .collect(Collectors.toList());

        byte b =1;
        if(userEntity.getGptCount() == 0){
            b=0;
        }


        return UserDTO.builder()
                .id(userEntity.getId())
                .uid(userEntity.getUid())
                .password(userEntity.getPassword())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .nickName(userEntity.getNickName())
                .simpleAddress(userEntity.getSimpleAddress())
                .createdAt(userEntity.getCreatedAt())
                .gptCount((userEntity.getGptCount()==0)? b:userEntity.getGptCount())
                .status(userEntity.getStatus())
                .gptList(gptdtoList)
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
