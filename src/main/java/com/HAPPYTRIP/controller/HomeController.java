package com.HAPPYTRIP.controller;


import com.HAPPYTRIP.api.Airline;
import com.HAPPYTRIP.api.AirlineApi;
import com.HAPPYTRIP.api.AirlineDto;
import com.HAPPYTRIP.domain.AirForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final AirlineApi airlineApi;
    @GetMapping("/home")
    public String home(AirForm airForm) {
        System.out.println("+++++++++++++++++++++");
        return "home";
    }

    @PostMapping("/home")
    public String searchFlights(@Valid AirForm airForm, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){
            // 검증 오류가 있는 경우
            System.out.println("검증 오류 발생:");
            // 모든 오류를 반복하며 로그에 출력
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }
            // 오류가 있으므로 home 페이지로 반환
            return "home";
        }


        try{

            HashMap<String,String> airMap=new HashMap<>();
                airMap.put("무안", "NAARKJB");
                airMap.put("광주", "NAARKJJ");
                airMap.put("군산", "NAARKJK");
                airMap.put("여수", "NAARKJY");
                airMap.put("원주", "NAARKNW");
                airMap.put("양양", "NAARKNY");
                airMap.put("제주", "NAARKPC");
                airMap.put("김해", "NAARKPK");
                airMap.put("사천", "NAARKPS");
                airMap.put("울산", "NAARKPU");
                airMap.put("인천", "NAARKSI");
                airMap.put("김포", "NAARKSS");
                airMap.put("포항", "NAARKTH");
                airMap.put("대구", "NAARKTN");
                airMap.put("청주", "NAARKTN");

            String tripType= airForm.getRoundWay();
            System.out.println("------------------------");
            System.out.println(airForm.getArrival()+airForm.getDeparture());
            String responseJson = null;

            if (tripType != null && tripType.equals("왕복")) {
                String roundWay1=airlineApi.getAirline(airMap.get(airForm.getDeparture()), airMap.get(airForm.getArrival()), airForm.getDepartureDate1(), "JJA");

                String roundWay2=airlineApi.getAirline(airForm.getArrival(), airForm.getDeparture(), airForm.getDepartureDate2(), "KAL");
            } else {
                responseJson=airlineApi.getAirline(airMap.get(airForm.getDeparture()), airMap.get(airForm.getArrival()), airForm.getDepartureDate1(), "KAL");
            }

            ObjectMapper objectMapper = new ObjectMapper();
            AirlineDto airlineResponse = objectMapper.readValue(responseJson, AirlineDto.class);


            AirlineDto.Body.Items items = airlineResponse.getResponse().getBody().getItems();


            if (items != null && items.getItem() != null) {
                // item 리스트를 가져옵니다.
                List<Airline> itemList = items.getItem();

                // 리스트의 각 항목을 출력하거나 처리합니다.
                for (Airline item : itemList) {
                    // 각 항목의 필드를 출력합니다.
                    System.out.println("Airline Name: " + item.getAirlineNm());
                    System.out.println("Arrival Airport Name: " + item.getArrAirportNm());
                    System.out.println("Arrival Planned Time: " + item.getArrPlandTime());
                    System.out.println("Departure Airport Name: " + item.getDepAirportNm());
                    System.out.println("Departure Planned Time: " + item.getDepPlandTime());
                    System.out.println("Economy Charge: " + item.getEconomyCharge());
                    System.out.println("Prestige Charge: " + item.getPrestigeCharge());
                    System.out.println("Vehicle ID: " + item.getVihicleId());
                    System.out.println(); // 각 항목 사이에 공백 출력
                }
                model.addAttribute("itemList", itemList);
            } else {
                // item 리스트가 비어있거나 null일 경우 메시지를 출력합니다.
                System.out.println("No items found in the list.");
            }




        }catch (Exception e){
            System.out.println("2222222222222222");
            e.printStackTrace();
            return "home";
        }

        return "booking";
    }
}
