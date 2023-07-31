package com.myproject.gympt.reply.service;

import com.myproject.gympt.Converter;
import com.myproject.gympt.reply.db.ReplyEntity;
import com.myproject.gympt.reply.model.ReplyDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReplyConverter implements Converter<ReplyDTO, ReplyEntity> {

    @Override
    public ReplyDTO toDto(ReplyEntity replyEntity) {

        return ReplyDTO
                .builder()
                .id(replyEntity.getId())
                .user(replyEntity.getUser().getId())
                .board(replyEntity.getBoard().getId())
                .content(replyEntity.getContent())
                .createdAt((replyEntity.getCreatedAt()==null)?LocalDateTime.now():replyEntity.getCreatedAt())
                .build()
                ;
    }

    @Override
    public ReplyEntity toEntity(ReplyDTO replyDTO) {
        return null;
    }
}
