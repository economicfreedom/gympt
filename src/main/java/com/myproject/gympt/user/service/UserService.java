package com.myproject.gympt.user.service;

import com.myproject.gympt.gpt.model.GPTDTO;
import com.myproject.gympt.user.db.UserEntity;
import com.myproject.gympt.user.db.UserRepository;
import com.myproject.gympt.user.model.UserDTO;
import com.myproject.gympt.user.model.UserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
@RequiredArgsConstructor
@Slf4j
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
                .status("Active")
                .email(userRequest.getEmail())
                .gptCount((byte) 0)
                .build();

        UserDTO dto = userConverter.toDto(user);
        userRepository.save(user);

        return dto;
    }
    public UserDTO getUser(String uid) throws DataFormatException {
        Optional<UserEntity> byUid = userRepository.findByUid(uid);
        if (byUid.isEmpty()){
            return null;
        }else {
            return getUserDTO(byUid);
        }




    }
    public UserDTO update(Principal principal){


        UserEntity userEntity = userRepository.findByUid(principal.getName()).get();
        byte b = (byte) (userEntity.getGptCount()+1);
        userEntity.setGptCount(b);
        userRepository.save(userEntity);
        UserDTO dto = userConverter.toDto(userEntity);
        return dto;
    }

        public UserDTO update(Principal principal,String password){


        UserEntity userEntity = userRepository.findByUid(principal.getName()).get();
        userEntity.setPassword(passwordEncoder.encode(password));
        userRepository.save(userEntity);
        UserDTO dto = userConverter.toDto(userEntity);
        return dto;
    }


    public UserDTO updatePassword(String uid,String email,String password){
        Optional<UserEntity> byUidAndEmail = userRepository.findByUidAndEmail(uid, email);
        if (byUidAndEmail.isEmpty()){
            log.info("값이 그냥  없음 ");
            return null;
        }else {

            UserEntity userEntity = byUidAndEmail.get();
            if (!userEntity.getStatus().equals("Active")){
                System.out.println("유저 상태 비교에서 막힘");
                return null;
            }

            userEntity.setPassword(password);

            userRepository.save(userEntity);
            UserDTO dto = userConverter.toDto(userEntity);

            System.out.println("안 막히고 리턴해줌");
            return dto;
        }

    }


    public boolean deleteUser(String password,Principal principal) throws DataFormatException {
        UserDTO user = getUser(principal.getName());
        log.info("유저 번호 {}",user.getId());
        if (!passwordEncoder.matches(password,user.getPassword())){
            return false;
        }
        UserEntity userEntity = userRepository.findByUid(principal.getName()).get();
        userEntity.setStatus("탈퇴");
        userRepository.save(userEntity);


        return true;

    }
    public String findUserId(String email){
        Optional<UserEntity> byEmail = userRepository.findByEmail(email);

        if (byEmail.isEmpty()){
            return null;
        }
        if (byEmail.get().getStatus().equals("Active")){
            return byEmail.get().getUid();
        }

        return null;
    }

    public List<GPTDTO> getGPTResponseList(String userId){
        UserEntity userEntity = userRepository.findByUid(userId).get();
        UserDTO dto = userConverter.toDto(userEntity);

        return dto.getGptList();
    }




    public boolean hasEmail(String email){
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
