package com.HAPPYTRIP.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Member {

    @Id
    @Column(name = "member_id")
    private Long id;

    private String userId;

    private String password;

    private String name;

    private String phone;

    private String birthday;







}
