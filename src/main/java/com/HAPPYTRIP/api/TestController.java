package com.HAPPYTRIP.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class TestController {

    @GetMapping("/airport")
    public @ResponseBody String airport() {
        try {
            AirlineApi.getAirline();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "ok";
    }
}
