package com.HAPPYTRIP.api;

import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final AirlineApi airlineApi;


    @GetMapping("/airport")
    public @ResponseBody String airport() {
        try {
            //AirlineApi.getAirline();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "ok";
    }
}
