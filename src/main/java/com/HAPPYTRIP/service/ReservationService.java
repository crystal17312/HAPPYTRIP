package com.HAPPYTRIP.service;

import com.HAPPYTRIP.domain.Flight;
import com.HAPPYTRIP.domain.Member;
import com.HAPPYTRIP.domain.Reservation;
import com.HAPPYTRIP.domain.ReservationStatus;
import com.HAPPYTRIP.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {


    private final ReservationRepository reservationRepository;

    public Reservation saveReservation(Member member, Flight flight,ReservationStatus status){
        Reservation reservation=new Reservation();
        reservation.setMemberId(member);
        reservation.setFlightId(flight);
        //reservation.setSeatCount(seatCount);
        reservation.setStatus(status);

        return reservationRepository.save(reservation);
    }

}




