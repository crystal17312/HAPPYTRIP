package com.HAPPYTRIP.repository;

import com.HAPPYTRIP.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight,Long> {
}
