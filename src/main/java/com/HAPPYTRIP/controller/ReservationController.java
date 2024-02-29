package com.HAPPYTRIP.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    @PostMapping("/reservation")
    public String reservation() {
        return "reservation";
    }
}
