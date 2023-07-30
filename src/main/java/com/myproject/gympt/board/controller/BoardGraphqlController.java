package com.myproject.gympt.board.controller;

import com.myproject.gympt.board.model.BoardDTO;
import com.myproject.gympt.board.model.BoardRequest;
import com.myproject.gympt.board.service.BoardService;
import com.myproject.gympt.user.model.UserDTO;
import com.myproject.gympt.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.zip.DataFormatException;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardGraphqlController {
    private final BoardService boardService;
    private final UserService userService;
    @QueryMapping
    public BoardDTO findById(@Argument Long id) {
        log.info("요청 들어옴");
        System.out.println("아규먼트 id 값 "+ id);
        return boardService.getBoard(id);
    }



    @MutationMapping
    @PreAuthorize("isAuthenticated()")
    public boolean deleteBoard(@Argument Long id, Principal principal) throws DataFormatException {
        System.out.println();
        UserDTO user = userService.getUser(principal.getName());

        if (!user.getUid().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        boardService.deleteBoard(id);
        return true;
    }

    @MutationMapping
    @PreAuthorize("isAuthenticated()")
    public boolean updateBoard(@Argument(name = "input") BoardRequest boardRequest, Principal principal)throws DataFormatException{
        log.info("start updateBoard Method");
        UserDTO user = userService.getUser(principal.getName());
        if (!user.getUid().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        boardService.modify(boardRequest);
        
        return true;
    }

    @PreAuthorize("isAuthenticated()")
    @MutationMapping
    public String createBoard(@Argument(name = "input") BoardRequest boardRequest,Principal principal

             ) throws DataFormatException {
        

        boardRequest.setUid(principal.getName());

        boardService.create(boardRequest);

        return "성공";
    }


}
