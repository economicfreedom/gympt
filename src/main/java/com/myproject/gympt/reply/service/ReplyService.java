package com.myproject.gympt.reply.service;

import com.myproject.gympt.board.model.BoardDTO;
import com.myproject.gympt.board.service.BoardConverter;
import com.myproject.gympt.board.service.BoardService;
import com.myproject.gympt.reply.db.ReplyEntity;
import com.myproject.gympt.reply.db.ReplyRepository;
import com.myproject.gympt.reply.model.ReplyDTO;
import com.myproject.gympt.reply.model.ReplyRequest;
import com.myproject.gympt.user.model.UserDTO;
import com.myproject.gympt.user.service.UserConverter;
import com.myproject.gympt.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
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
    public ReplyDTO create(ReplyRequest replyRequest, Principal principal) throws DataFormatException {
        UserDTO user = userService.getUser(principal.getName());
        BoardDTO board = boardService.getBoard(replyRequest.getBoardId());


        ReplyEntity entity = ReplyEntity.builder()
                .board(boardConverter.toEntity(board))
                .user(userConverter.toEntity(user))
                .content(replyRequest.getContent())
                .createdAt(LocalDateTime.now())
                .build();
        ReplyEntity save = replyRepository.save(entity);

        return replyConverter.toDto(entity);


    }
}
