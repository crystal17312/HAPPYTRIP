package com.HAPPYTRIP.domain;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Flight {

    @Id
    @Column(name = "Flight_id")
    private Long id;

    private String flightNumber;

    private String departure;

    private String arrival;

    private LocalDateTime departureDate;

    private LocalDateTime arrivalDate;

    private String airlineName;

    private int price;
}
