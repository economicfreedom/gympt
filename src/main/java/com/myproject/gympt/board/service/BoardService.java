package com.myproject.gympt.board.service;

import com.myproject.gympt.board.db.BoardEntity;
import com.myproject.gympt.board.db.BoardRepository;
import com.myproject.gympt.board.model.BoardDTO;
import com.myproject.gympt.board.model.BoardRequest;
import com.myproject.gympt.comon.Api;
import com.myproject.gympt.comon.Pagination;
import com.myproject.gympt.user.db.UserEntity;
import com.myproject.gympt.user.db.UserRepository;
import com.myproject.gympt.user.service.UserConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService  {
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

    public BoardDTO modify(BoardRequest boardRequest) {

        Optional<UserEntity> byUid = userRepository.findByUid(boardRequest.getUid());

        Optional<BoardEntity> boardEntity = boardRepository.findById(boardRequest.getId());
        BoardEntity board = boardEntity.get();
        board.setContent(boardRequest.getContent());
        board.setTitle(board.getTitle());
        board.setBoardType(board.getBoardType());
        board.setCreatedAt(LocalDateTime.now());

        return boardConverter.toDto(boardRepository.save(board));
    }

    public Api<List<BoardEntity>> all(Pageable pageable, String title, String type) {
        title = "%"+title+"%";
        type = "%"+type+"%";
        log.info("들어온 타이틀 {}",title);
        log.info("들어온 타입 {}",type);

        Page<BoardEntity> list = boardRepository.findByTitleLikeAndBoardTypeLike(pageable, title, type);

        Pagination pagination = Pagination.builder()
                .page(list.getNumber())//현재 페이지
                .size(list.getSize())// 사이즈 몇개를 요청 했는지에 대한 숫자
                .currentElements(list.getNumberOfElements()) // 현재 페이지의 게시글
                .totalElements(list.getTotalElements()) // 전체 게시물
                .totalPage(list.getTotalPages())
                .build();// 페이지가 몇번까지 있는지
        Api<List<BoardEntity>> response = Api.<List<BoardEntity>>builder()
                .body(list.toList())
                .pagination(pagination)
                .build();


        return response;
    }

    public BoardDTO getBoard (Long id){
        Optional<BoardEntity> boardEntity = boardRepository.findById(id);
        if (boardEntity.isEmpty()){
            return null;
        }else {

        BoardDTO dto = boardConverter.toDto(boardEntity.get());
        return dto;
        }

    }

//    public BoardDTO getBoard(Long id){
//        boardRepository.save()
//        return
//
//    }
    public void deleteBoard(Long id){
        boardRepository.deleteById(id);
    }
    public Long getMaxId(){
        return boardRepository.maxId();
    }
}


