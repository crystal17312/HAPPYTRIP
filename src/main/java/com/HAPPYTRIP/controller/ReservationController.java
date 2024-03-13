package com.HAPPYTRIP.controller;

import com.HAPPYTRIP.domain.ReservationDTO;
import com.HAPPYTRIP.domain.SelectedFlightsDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/booking")
@RequiredArgsConstructor
@Slf4j
public class ReservationController {

    private final ObjectMapper objectMapper;

    @PostMapping("/submitFlights")
    @ResponseBody
    public String submitFlights(@RequestBody String jsonString) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        SelectedFlightsDTO selectedFlightsDTO = objectMapper.readValue(jsonString, SelectedFlightsDTO.class);
        List<ReservationDTO> selectedFlights1 = selectedFlightsDTO.getSelectedFlights1();
        List<ReservationDTO> selectedFlights2 = selectedFlightsDTO.getSelectedFlights2();

        // 데이터 처리 로직
        log.info("-----------------");
        log.info("Received flight : {} ",selectedFlights1);
        log.info("Received flight : {} ",selectedFlights2);

        return "success";
    }

//    @PostMapping("/submitFlights")
//    @ResponseBody
//    public ResponseEntity<String> submitFlights(@RequestBody String selectFlights) throws  Exception {
//        log.info("-----------------------");
//        log.info("Received flight : {} ", selectFlights);
//
//        // JSON 문자열을 자바 객체로 변환
//        List<Map<String, Object>> flightData = objectMapper.readValue(selectFlights, new TypeReference<List<Map<String, Object>>>(){});
//
//        // 여기서 flightData를 이용하여 필요한 작업 수행
//
//        // 응답 데이터를 JSON 문자열로 변환하여 반환
//        String response = objectMapper.writeValueAsString(flightData);
//        return ResponseEntity.ok(response);
//    }
}
