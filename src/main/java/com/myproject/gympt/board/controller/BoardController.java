package com.myproject.gympt.board.controller;

import com.myproject.gympt.board.db.BoardEntity;
import com.myproject.gympt.board.model.BoardRequest;
import com.myproject.gympt.board.service.BoardService;
import com.myproject.gympt.comon.Api;
import com.myproject.gympt.comon.Pagination;
import com.myproject.gympt.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
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
    private Map<String, Long> lastRequestTimes = new ConcurrentHashMap<>();
private final long requestInterval = 1000; // 2초 (밀리초 단위)

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
    public String create(BoardRequest boardRequest) {
        return "createForm";

    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String create(@Valid BoardRequest boardRequest
            , BindingResult bindingResult
            , Principal principal) throws DataFormatException {
        if (bindingResult.hasErrors()){
            return "createForm";
        }

        boardRequest.setUid(principal.getName());

        boardService.create(boardRequest);

        return "redirect:/";
    }


    @GetMapping("/all")
    @ResponseBody
    public Api<List<BoardEntity>> list(
            @PageableDefault(page =  0,size = 10,sort = "id",direction = Sort.Direction.DESC)
            Pageable pageable,
            @RequestParam(value = "title",defaultValue = "") String title
            ,@RequestParam(value = "type",defaultValue = "") String type
            ,@RequestHeader("X-Forwarded-For") String ipAddress

    ) {
    String clientId = ipAddress; // 클라이언트를 식별하는 정보를 가져오는 메서드를 가정

    long currentTime = System.currentTimeMillis();
    long lastRequestTime = lastRequestTimes.getOrDefault(clientId, 0L);
    if (currentTime - lastRequestTime >= requestInterval) {
        lastRequestTimes.put(clientId, currentTime); // 요청 처리 시간 기록
        Pagination pagination = boardService.all(pageable, title, type).getPagination();

        log.info("페이징 토탈데이터 {}", pagination.getTotalElements());
        log.info("페이징 토탈페이지 {}", pagination.getTotalPage());
        return boardService.all(pageable, title, type);
    } else {
        // 일정 시간 이내에 요청이 들어온 경우 요청을 거부하거나 예외 처리할 수 있습니다.
        // 여기서는 null 또는 오류 응답을 반환하도록 가정합니다.
        return null;
    }
    }

    @GetMapping("/main")
    public String listPage(){
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


}
