package com.myproject.gympt.board.db;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.myproject.gympt.reply.db.ReplyEntity;
import com.myproject.gympt.user.db.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "board")
@Entity
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "board_type", nullable = false)
    private String boardType;

    @Column(name = "nick_name", nullable = false)
    private String nickName;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
        @JsonManagedReference

    private List<ReplyEntity> replyList;
    @Formula("CASE WHEN CHAR_LENGTH(content) > 10 THEN CONCAT(SUBSTRING(content, 1, 10), '...') ELSE content END")
    private String truncatedContent;

    // Constructors, getters, and setters (omitted for brevity)
}
