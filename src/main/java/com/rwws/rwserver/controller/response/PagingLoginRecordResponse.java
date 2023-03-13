package com.rwws.rwserver.controller.response;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class PagingLoginRecordResponse extends PagingResponse {
    private List<LoginRecord> loginRecords = List.of();

    @Data
    public static class LoginRecord {
        private Long userId;
        private String ip;
        private Instant time;
    }
}
