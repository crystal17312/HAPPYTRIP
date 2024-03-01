package com.HAPPYTRIP.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Airline {
    @JsonProperty("airlineNm")
    private String airlineNm;

    @JsonProperty("arrAirportNm")
    private String arrAirportNm;

    @JsonProperty("arrPlandTime")
    private String arrPlandTime;

    @JsonProperty("depAirportNm")
    private String depAirportNm;

    @JsonProperty("depPlandTime")
    private String depPlandTime;

    @JsonProperty("economyCharge")
    private int economyCharge;

    @JsonProperty("prestigeCharge")
    private int prestigeCharge;

    @JsonProperty("vihicleId")
    private String vihicleId;

    // getter 및 setter 메서드
}