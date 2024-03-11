package com.HAPPYTRIP.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Reservation {

    @Id
    @Column(name = "reservation_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member memberId;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flightId;

    private int seatCount;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;


}
