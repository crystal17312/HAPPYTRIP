package com.HAPPYTRIP.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class AirlineDto {
    private Response response;

    @Data
    @AllArgsConstructor
    class Response {
        private Header header;
        private Body body;
    }

    @Data
    @AllArgsConstructor
    class Header {
        private String resultCode;
        private String resultMsg;
    }

    @Data
    @AllArgsConstructor
    class Body {
        private Items items;
        private int numOfRows;
        private int pageNo;
        private int totalCount;

        @Data
        @AllArgsConstructor
        class Items {
            private List<Airline> item;
        }
    }

}
