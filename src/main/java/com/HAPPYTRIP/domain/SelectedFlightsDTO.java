package com.HAPPYTRIP.domain;

import lombok.Data;

import java.util.List;

@Data
public class SelectedFlightsDTO {
    private List<ReservationDTO> selectedFlights1;
    private List<ReservationDTO> selectedFlights2;
}
