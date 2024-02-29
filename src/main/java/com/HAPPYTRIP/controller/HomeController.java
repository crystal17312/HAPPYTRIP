package com.HAPPYTRIP.controller;


import com.HAPPYTRIP.api.AirlineApi;
import com.HAPPYTRIP.domain.AirForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final AirlineApi airlineApi;
    @GetMapping("/home")
    public String home(AirForm airForm) {
        return "home";
    }

    @PostMapping("/home")
    public String searchFlights(@Valid AirForm airForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "home";
        }

        String tripType= airForm.getRoundWay();

        System.out.println(airForm.getArrival()+airForm.getDeparture());



        try{

            if (tripType != null && tripType.equals("왕복")) {
                airlineApi.getAirline(airForm.getDeparture(), airForm.getArrival(), airForm.getDepartureDate1(), "kAL");

                airlineApi.getAirline(airForm.getArrival(), airForm.getDeparture(), airForm.getDepartureDate2(), "KAL");
            } else {
                airlineApi.getAirline(airForm.getDeparture(), airForm.getArrival(), airForm.getDepartureDate1(), "KAL");
            }

        }catch (Exception e){
            e.printStackTrace();
            return "home";
        }

        return "booking/list";
    }
}
