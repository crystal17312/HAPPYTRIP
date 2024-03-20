package com.HAPPYTRIP.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Notice_id")
    private Long id;

    private String title;

    private String content;

    private LocalDateTime date;
}
