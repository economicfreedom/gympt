package com.myproject.gympt.gpt.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.myproject.gympt.board.model.BoardDTO;
import com.myproject.gympt.user.db.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "gpt")
public class GptEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "request", nullable = false, columnDefinition = "TEXT")
    private String request;

    @Column(name = "response", nullable = false, columnDefinition = "MEDIUMTEXT")
    private String response;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id" )
@JsonBackReference
private UserEntity user;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name ="type")
    private String type;
    // Constructors, getters, and setters (omitted for brevity)

}

