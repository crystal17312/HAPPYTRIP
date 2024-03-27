package com.HAPPYTRIP.repository;

import com.HAPPYTRIP.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

}
