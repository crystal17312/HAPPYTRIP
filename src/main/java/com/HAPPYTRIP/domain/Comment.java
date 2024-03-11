package com.HAPPYTRIP.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Comment {

    @Id
    @Column(name = "Comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board boardId;

    private String content;

    private LocalDateTime date;
}
