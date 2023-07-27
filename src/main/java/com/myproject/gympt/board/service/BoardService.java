package com.myproject.gympt.board.service;

import com.myproject.gympt.board.db.BoardEntity;
import com.myproject.gympt.board.db.BoardRepository;
import com.myproject.gympt.board.model.BoardDTO;
import com.myproject.gympt.board.model.BoardRequest;
import com.myproject.gympt.user.db.UserEntity;
import com.myproject.gympt.user.db.UserRepository;
import com.myproject.gympt.user.model.UserRequest;
import com.myproject.gympt.user.service.UserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserConverter userConverter;
    private final UserRepository userRepository;
    private final BoardConverter boardConverter;

    public BoardDTO create(BoardRequest boardRequest) {

        Optional<UserEntity> byUid = userRepository.findByUid(boardRequest.getUid());

        BoardEntity boardEntity = BoardEntity.builder()
                .title(boardRequest.getTitle())
                .boardType(boardRequest.getBoardType())
                .user(byUid.get())
                .content(boardRequest.getContent())
                .nickName(byUid.get().getNickName())
                .createdAt(LocalDateTime.now())
                .replyList(null)
                .build();
        return boardConverter.toDto(boardRepository.save(boardEntity));
    }


}
