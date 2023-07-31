package com.myproject.gympt.reply.db;

import com.myproject.gympt.board.db.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository  extends JpaRepository<ReplyEntity,Long> {
    List<ReplyEntity> findAllByBoardId(Long id);
}
