package com.HAPPYTRIP.controller;

import com.HAPPYTRIP.api.Airline;
import com.HAPPYTRIP.api.AirlineApi;
import com.HAPPYTRIP.api.AirlineDto;
import com.HAPPYTRIP.domain.*;
import com.HAPPYTRIP.dto.AirForm;
import com.HAPPYTRIP.dto.MemberForm;
import com.HAPPYTRIP.dto.ReservationDTO;
import com.HAPPYTRIP.dto.SelectedFlightsDTO;
import com.HAPPYTRIP.service.FlightService;
import com.HAPPYTRIP.service.MemberService;
import com.HAPPYTRIP.service.ReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ReservationController {

    private final ObjectMapper objectMapper;
    private final AirlineApi airlineApi;
    private final FlightService flightService;
    private final ReservationService reservationService;
    private final MemberService memberService;

    @PostMapping("/booking")
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

            log.info("------------------------");
            log.info(airForm.getArrival()+airForm.getDeparture());
            log.info("airFormPassenger {}",airForm.getPassenger());



            if (tripType != null && tripType.equals("왕복")) {
                String roundWayResponseJson1=airlineApi.getAirline(airMap.get(airForm.getDeparture()), airMap.get(airForm.getArrival()), airForm.getDepartureDate1(), "KAL");
                String roundWayResponseJson2=airlineApi.getAirline(airMap.get(airForm.getArrival()), airMap.get(airForm.getDeparture()), airForm.getDepartureDate2(), "KAL");
                ObjectMapper objectMapper1 = new ObjectMapper();


                AirlineDto airlineResponse1 = objectMapper1.readValue(roundWayResponseJson1, AirlineDto.class);
                AirlineDto.Body.Items items1 = airlineResponse1.getResponse().getBody().getItems();
                if(items1 !=null && items1.getItem() !=null){
                    List<Airline> itemList1=items1.getItem();


                    for (Airline item : itemList1) {

                        System.out.println("Airline Name: " + item.getAirlineNm());
                        System.out.println("Arrival Airport Name: " + item.getArrAirportNm());
                        System.out.println("Arrival Planned Time: " + item.getArrPlandTime());
                        System.out.println("Departure Airport Name: " + item.getDepAirportNm());
                        System.out.println("Departure Planned Time: " + item.getDepPlandTime());
                        System.out.println("Economy Charge: " + item.getEconomyCharge());
                        System.out.println("Prestige Charge: " + item.getPrestigeCharge());
                        System.out.println("Vehicle ID: " + item.getVihicleId());
                        System.out.println();
                        String departureTime=item.getDepPlandTime();
                        String arrivalTime=item.getArrPlandTime();

                        SimpleDateFormat format = new SimpleDateFormat("HHmm");
                        Date dpaTime=format.parse(departureTime.substring(8,12));
                        Date arTime = format.parse(arrivalTime.substring(8,12));
                        long diff = arTime.getTime() - dpaTime.getTime();
                        if (diff < 0) {
                            diff += 24 * 60 * 60 * 1000; // 24시간을 더해줌
                        }
                        long diffMinutes = diff / (60 * 1000);
                        long hours = diffMinutes / 60;
                        long minutes = diffMinutes % 60;
                        String durationTime1 = hours + ":" + minutes;

                        item.setDuration(durationTime1);
                        item.setPassenger(airForm.getPassenger());

                    }

                    model.addAttribute("itemList1",itemList1);
                }

                ObjectMapper objectMapper2=new ObjectMapper();

                AirlineDto airlineResponse2=objectMapper2.readValue(roundWayResponseJson2,AirlineDto.class);
                AirlineDto.Body.Items items2=airlineResponse2.getResponse().getBody().getItems();
                if(items2 !=null && items2.getItem() !=null){
                    List<Airline> itemList2=items2.getItem();

                    for (Airline item : itemList2) {

                        System.out.println("Airline Name: " + item.getAirlineNm());
                        System.out.println("Arrival Airport Name: " + item.getArrAirportNm());
                        System.out.println("Arrival Planned Time: " + item.getArrPlandTime());
                        System.out.println("Departure Airport Name: " + item.getDepAirportNm());
                        System.out.println("Departure Planned Time: " + item.getDepPlandTime());
                        System.out.println("Economy Charge: " + item.getEconomyCharge());
                        System.out.println("Prestige Charge: " + item.getPrestigeCharge());
                        System.out.println("Vehicle ID: " + item.getVihicleId());
                        System.out.println();

                        String departureTime=item.getDepPlandTime();
                        String arrivalTime=item.getArrPlandTime();

                        SimpleDateFormat format = new SimpleDateFormat("HHmm");
                        Date dpaTime=format.parse(departureTime.substring(8,12));
                        Date arTime = format.parse(arrivalTime.substring(8,12));
                        long diff = arTime.getTime() - dpaTime.getTime();
                        if (diff < 0) {
                            diff += 24 * 60 * 60 * 1000; // 24시간을 더해줌
                        }
                        long diffMinutes = diff / (60 * 1000);
                        long hours = diffMinutes / 60;
                        long minutes = diffMinutes % 60;
                        String durationTime2 = hours + ":" + minutes;
                        item.setDuration(durationTime2);
                    }

                    model.addAttribute("itemList2",itemList2);
                }
                model.addAttribute("tripType",tripType);





            } else {
                String oneWayResponseJson=airlineApi.getAirline(airMap.get(airForm.getDeparture()), airMap.get(airForm.getArrival()), airForm.getDepartureDate1(), "KAL");
                Integer passenger=airForm.getPassenger();
                ObjectMapper objectMapper = new ObjectMapper();
                AirlineDto airlineResponse = objectMapper.readValue(oneWayResponseJson, AirlineDto.class);


                AirlineDto.Body.Items items = airlineResponse.getResponse().getBody().getItems();
                if (items != null && items.getItem() != null) {
                    // item 리스트를 가져옵니다.
                    List<Airline> itemList1 = items.getItem();

                    // 리스트의 각 항목을 출력하거나 처리합니다.
                    for (Airline item : itemList1) {
                        // 각 항목의 필드를 출력합니다.
                        System.out.println("Airline Name: " + item.getAirlineNm());
                        System.out.println("Arrival Airport Name: " + item.getArrAirportNm());
                        System.out.println("Arrival Planned Time: " + item.getArrPlandTime());
                        System.out.println("Departure Airport Name: " + item.getDepAirportNm());
                        System.out.println("Departure Planned Time: " + item.getDepPlandTime());
                        System.out.println("Economy Charge: " + item.getEconomyCharge());
                        System.out.println("Prestige Charge: " + item.getPrestigeCharge());
                        System.out.println("Vehicle ID: " + item.getVihicleId());
                        System.out.println();

                        String departureTime=item.getDepPlandTime();
                        String arrivalTime=item.getArrPlandTime();

                        SimpleDateFormat format = new SimpleDateFormat("HHmm");
                        Date dpaTime=format.parse(departureTime.substring(8,12));
                        Date arTime = format.parse(arrivalTime.substring(8,12));
                        long diff = arTime.getTime() - dpaTime.getTime();
                        if (diff < 0) {
                            diff += 24 * 60 * 60 * 1000;
                        }
                        long diffMinutes = diff / (60 * 1000);
                        long hours = diffMinutes / 60;
                        long minutes = diffMinutes % 60;
                        String durationTime1 = hours + ":" + minutes;
                        item.setDuration(durationTime1);
                        item.setPassenger(airForm.getPassenger());
                    }
                    model.addAttribute("itemList1", itemList1);
                } else {
                    // item 리스트가 비어있거나 null일 경우 메시지를 출력합니다.
                    System.out.println("No items found in the list.");
                }
            }


        }catch (Exception e){
            System.out.println("2222222222222222");
            e.printStackTrace();
            return "home";
        }

        return "booking";
    }

    @PostMapping("/booking/submitFlights")
    public String submitFlights(@RequestBody String jsonString, Model model , HttpSession session) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        SelectedFlightsDTO selectedFlightsDTO = objectMapper.readValue(jsonString, SelectedFlightsDTO.class);
        List<ReservationDTO> selectedFlights1 = selectedFlightsDTO.getSelectedFlights1();
        List<ReservationDTO> selectedFlights2 = selectedFlightsDTO.getSelectedFlights2();

        // 데이터 처리 로직
        log.info("-----------------");
//        log.info("Received flight : {} ",selectedFlights1);
//        log.info("Received flight : {} ",selectedFlights2);

        session.setAttribute("selectedFlights1",selectedFlights1);
        session.setAttribute("selectedFlights2",selectedFlights2);
        System.out.println("Session attribute 'selectedFlights1': " + selectedFlights1);
        System.out.println("Session attribute 'selectedFlights2': " + selectedFlights2);


        return "redirect:/booking/pay";
    }



    @GetMapping("/booking/pay")
    public String showPaymentPage(Model model, HttpSession session) {
        List<ReservationDTO> selectedFlights1 = (List<ReservationDTO>) session.getAttribute("selectedFlights1");
        List<ReservationDTO> selectedFlights2 = (List<ReservationDTO>) session.getAttribute("selectedFlights2");

        // 가져온 데이터를 모델에 추가
        model.addAttribute("selectedFlights1", selectedFlights1);
        model.addAttribute("selectedFlights2", selectedFlights2);

        return "pay";
    }
    @PostMapping("/booking/pay")
    public String payment(Model model, HttpSession session,Principal principal){


        List<ReservationDTO> selectedFlights1=(List<ReservationDTO>)(List<ReservationDTO>) session.getAttribute("selectedFlights1");
        List<ReservationDTO> selectedFlights2=(List<ReservationDTO>)(List<ReservationDTO>) session.getAttribute("selectedFlights2");
        model.addAttribute("selectedFlights1",selectedFlights1);
        model.addAttribute("selectedFlights2",selectedFlights2);

        log.info("selectedFlights1 = {}",selectedFlights1);
        log.info("selectedFlights2 = {}",selectedFlights2);


        Flight flight1 = new Flight();


        String username=principal.getName();
        Optional<Member> optionalMember=memberService.findByUserId(username);
        Member member=optionalMember.get();

        for (ReservationDTO reservationDTO : selectedFlights1) {

            flight1.setFlightNumber(reservationDTO.getFlightNumber());
            flight1.setDeparture(reservationDTO.getDeparture());
            flight1.setArrival(reservationDTO.getArrival());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
            flight1.setDepartureDate(LocalDateTime.parse(reservationDTO.getDepartureTime(),formatter));
            flight1.setArrivalDate(LocalDateTime.parse(reservationDTO.getArrivalTime(),formatter));
            flight1.setAirlineName(reservationDTO.getAirline());
            flight1.setPrice(Integer.parseInt(reservationDTO.getPrice()));

        }

        flightService.saveFlight(flight1);
        reservationService.saveReservation(member,flight1, ReservationStatus.COMPLETION);

        if(!selectedFlights2.isEmpty()){
            Flight flight2 = new Flight();

            for (ReservationDTO reservationDTO : selectedFlights2) {


                    flight2.setFlightNumber(reservationDTO.getFlightNumber());
                    flight2.setDeparture(reservationDTO.getDeparture());
                    flight2.setArrival(reservationDTO.getArrival());
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
                    flight2.setDepartureDate(LocalDateTime.parse(reservationDTO.getDepartureTime(), formatter));
                    flight2.setArrivalDate(LocalDateTime.parse(reservationDTO.getArrivalTime(), formatter));
                    flight2.setAirlineName(reservationDTO.getAirline());
                    flight2.setPrice(Integer.parseInt(reservationDTO.getPrice()));
                }
            flightService.saveFlight(flight2);
            reservationService.saveReservation(member,flight2,ReservationStatus.COMPLETION);


        }


        return "redirect:/home";

    }




}
