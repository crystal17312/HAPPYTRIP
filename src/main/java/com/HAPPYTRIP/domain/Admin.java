package com.HAPPYTRIP.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Admin {

    @Id
    @Column(name = "Admin_id")
    private Long id;

    private String admin;

    private String password;
}
