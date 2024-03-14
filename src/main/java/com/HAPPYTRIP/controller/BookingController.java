package com.HAPPYTRIP.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pay")
@PreAuthorize("isAuthenticated()")
public class BookingController {

    @GetMapping("")
    public String pay() {
        return "pay";
    }
}
