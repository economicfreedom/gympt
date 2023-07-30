package com.myproject.gympt.board.db;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.graphql.data.GraphQlRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@GraphQlRepository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    @Query("SELECT b FROM BoardEntity b WHERE b.title LIKE :title AND b.boardType LIKE :type")
    Page<BoardEntity> findByTitleLikeAndBoardTypeLike(Pageable pageable, @Param("title") String title, @Param("type") String type);


    Page<BoardEntity> findAll(Pageable pageable);

    @Query("select max(b.id) from BoardEntity b")
    Long maxId();



}
