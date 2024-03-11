package com.HAPPYTRIP.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Board {

    @Id
    @Column(name = "Board_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member memberId;

    private String title;

    private String content;

    private LocalDateTime date;
}
