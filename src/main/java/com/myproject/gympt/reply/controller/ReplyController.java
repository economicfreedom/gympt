package com.myproject.gympt.reply.controller;

import com.myproject.gympt.board.db.BoardEntity;
import com.myproject.gympt.board.model.BoardDTO;
import com.myproject.gympt.reply.model.ReplyDTO;
import com.myproject.gympt.reply.model.ReplyRequest;
import com.myproject.gympt.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.zip.DataFormatException;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ReplyController {
    private final ReplyService replyService;



    @PreAuthorize("isAuthenticated()")
    @MutationMapping
    public ReplyDTO addReply(@Argument(name = "input")ReplyRequest replyRequest, Principal principal) throws DataFormatException {
        log.info("addReply reply request === > {} ",replyRequest);
        System.out.println("addReply 요청 들어옴");
        ReplyDTO replyDTO = replyService.create(replyRequest, principal);
        log.info("replyDTO {}" ,replyDTO.getUserEntity().toString());
        return replyDTO;
    }

    @PreAuthorize("isAuthenticated()")
    @MutationMapping
    public Boolean updateReply(@Argument(name = "input")ReplyRequest replyRequest){

        log.info("updateReply reply request === > {} ",replyRequest);
        Boolean update = replyService.update(replyRequest);

        return update;
    }

    @PreAuthorize("isAuthenticated()")
    @MutationMapping
    public Boolean deleteReply(@Argument(name = "input")ReplyRequest replyRequest){
        log.info("deleteReply reply request === > {} ",replyRequest);
        return  replyService.delete(replyRequest);
    }
}
