package com.HAPPYTRIP.service;


import com.HAPPYTRIP.repository.AirlineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AirlineService {

    private final AirlineRepository airlineRepository;

//    @Transactional
//    public void saveAir(){
//        Flight flight=new Flight();
//        flight.setAirlineName();
//        flight.setFlightNumber();
//        flight.setArrival();
//        flight.setDeparture();
//        flight.setDepartureDate();
//        flight.setArrivalDate();
//        flight.setAirlineName();
//        flight.setPrice();
//    }


}
