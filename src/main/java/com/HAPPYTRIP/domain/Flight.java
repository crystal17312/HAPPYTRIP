package com.HAPPYTRIP.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;

    private String flightNumber;

    private String departure;

    private String arrival;

    private LocalDateTime departureDate;

    private LocalDateTime arrivalDate;

    private String airlineName;
}
