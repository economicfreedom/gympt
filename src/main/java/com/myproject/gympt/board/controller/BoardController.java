package com.myproject.gympt.board.controller;

import com.myproject.gympt.board.db.BoardEntity;
import com.myproject.gympt.board.model.BoardDTO;
import com.myproject.gympt.board.model.BoardRequest;
import com.myproject.gympt.board.service.BoardService;
import com.myproject.gympt.comon.Api;
import com.myproject.gympt.comon.Pagination;
import com.myproject.gympt.user.model.UserDTO;
import com.myproject.gympt.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.graphql.data.method.annotation.Arguments;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.DataFormatException;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final UserService userService;
    private final BoardService boardService;


    @GetMapping("")
    public String mainPage() {
        return "Test";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/user/login")
    public String loginForm() {
        return "loginForm";
    }


    public String board() {
        return "main";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String create(BoardRequest boardRequest,Model model) {
        model.addAttribute("maxId",boardService.getMaxId());

        return "createForm";

    }

//    @PreAuthorize("isAuthenticated()")
//    @PostMapping("/create")
//    public String create(@Valid BoardRequest boardRequest
//            , BindingResult bindingResult
//            , Principal principal) throws DataFormatException {
//        if (bindingResult.hasErrors()) {
//            return "createForm";
//        }
//
//        boardRequest.setUid(principal.getName());
//
//        boardService.create(boardRequest);
//
//        return "redirect:/";
//    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String create(
            @PathVariable Long id
            , Model model) throws DataFormatException {


        BoardDTO board = boardService.getBoard(id);
        model.addAttribute("board", board);


        return "modify";
    }


    @GetMapping("/all")
    @ResponseBody
    public Api<List<BoardEntity>> list(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable,
            @RequestParam(value = "title", defaultValue = "") String title
            , @RequestParam(value = "type", defaultValue = "") String type


    ) {

        Pagination pagination = boardService.all(pageable, title, type).getPagination();

        log.info("페이징 토탈데이터 {}", pagination.getTotalElements());
        log.info("페이징 토탈페이지 {}", pagination.getTotalPage());
        return boardService.all(pageable, title, type);

    }

    @GetMapping("/main")
    public String listPage() {
        return "main";
    }

//    public String list(Model model ,@RequestParam(value = "page",defaultValue ="0")int page,
//                       @RequestParam(value = "kw",defaultValue = "") String kw){
//        log.info("page : {}, kw : {}",page,kw);
//        Page<BoardEntity> paging =
//
//
//
//    }

    @GetMapping("/detail/{id}")
    public String boardPage(@PathVariable Long id, Model model, Principal principal) throws DataFormatException {
        BoardDTO board = boardService.getBoard(id);


        model.addAttribute("uid", board.getUser().getUid());
        model.addAttribute("testId", id);

        return "detail";
    }


}
