package com.rwws.rwserver.controller.response;

import lombok.Data;

@Data
public class PagingResponse {
    private long total;
    private long size;
    private long current;
}
