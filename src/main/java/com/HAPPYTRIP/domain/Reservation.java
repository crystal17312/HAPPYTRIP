package com.HAPPYTRIP.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @ManyToOne
    private Member memberId;

    @ManyToOne
    private Flight flightId;

    private int seatCount;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;


}
