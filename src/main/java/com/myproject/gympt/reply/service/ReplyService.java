package com.myproject.gympt.reply.service;

import com.myproject.gympt.board.model.BoardDTO;
import com.myproject.gympt.board.service.BoardConverter;
import com.myproject.gympt.board.service.BoardService;
import com.myproject.gympt.reply.db.ReplyEntity;
import com.myproject.gympt.reply.db.ReplyRepository;
import com.myproject.gympt.reply.model.ReplyDTO;
import com.myproject.gympt.reply.model.ReplyRequest;
import com.myproject.gympt.user.db.UserEntity;
import com.myproject.gympt.user.db.UserRepository;
import com.myproject.gympt.user.model.UserDTO;
import com.myproject.gympt.user.service.UserConverter;
import com.myproject.gympt.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final UserService userService;
    private final BoardService boardService;
    private final UserConverter userConverter;
    private final BoardConverter boardConverter;
    private final ReplyConverter replyConverter;
    private final UserRepository userRepository;
    public ReplyDTO create(ReplyRequest replyRequest, Principal principal) throws DataFormatException {
        UserDTO user = userService.getUser(principal.getName());
        BoardDTO board = boardService.getBoard(replyRequest.getBoardId());
        UserEntity userEntity = userRepository.findByUid(principal.getName()).get();
        log.info("유저 닉네임 {}, 유저 유아이디{}",userEntity.getNickName(),userEntity.getUid());

        log.info("얜 뭐임 ? {}",user.getUid());
        ReplyEntity entity = ReplyEntity.builder()
                .board(boardConverter.toEntity(board))
                .user(userConverter.toEntity(user))
                .content(replyRequest.getContent())
                .createdAt(LocalDateTime.now())
                .build();
        ReplyEntity save = replyRepository.save(entity);

        ReplyDTO dto = replyConverter.toDto(entity);

        return dto;


    }

    public Boolean update(ReplyRequest replyRequest){
        Optional<ReplyEntity> reply = replyRepository.findById(replyRequest.getId());

        ReplyEntity replyEntity = reply.get();
        replyEntity.setContent(replyRequest.getContent());

        replyRepository.save(replyEntity);

        return true;
    }
    public Boolean delete(ReplyRequest replyRequest){
        replyRepository.deleteById(replyRequest.getId());
        return true;

    }
}
