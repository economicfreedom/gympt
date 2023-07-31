package com.myproject.gympt.board.service;

import com.myproject.gympt.Converter;
import com.myproject.gympt.board.db.BoardEntity;
import com.myproject.gympt.board.db.BoardRepository;
import com.myproject.gympt.board.model.BoardDTO;
import com.myproject.gympt.reply.db.ReplyEntity;
import com.myproject.gympt.reply.db.ReplyRepository;
import com.myproject.gympt.user.db.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardConverter implements Converter<BoardDTO, BoardEntity> {
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;
    @Override
    public BoardDTO toDto(BoardEntity boardEntity) {
        List<ReplyEntity> allByBoardId = replyRepository.findAllByBoardId(boardEntity.getId());



        return BoardDTO.builder()
                .id(boardEntity.getId())
                .nickName(boardEntity.getNickName())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .createdAt(boardEntity.getCreatedAt())
                .replyList(boardEntity.getReplyList())
                .user(boardEntity.getUser())
                .boardType(boardEntity.getBoardType())
                .replyList(allByBoardId)
                .build();

    }

    @Override
    public BoardEntity toEntity(BoardDTO boardDTO) {
        Optional<BoardEntity> byId = boardRepository.findById(boardDTO.getId());

        return byId.get();
    }



}
