package com.myproject.gympt.user.db;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.myproject.gympt.gpt.db.GptEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "user")
@Entity

public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "simple_addr", nullable = false)
    private String simpleAddress;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "uid", nullable = false, unique = true)
    private String uid;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nick_name", nullable = false, unique = true)
    private String nickName;
    @Column(name = "gpt_count")
    private byte gptCount;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    @OrderBy("id desc")
    @Builder.Default
            @JsonManagedReference

    private List<GptEntity> gptEntities = List.of();
    @ColumnDefault("Active")
    @Column(name = "status", nullable = false)
    private String status;


}
