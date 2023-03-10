package com.rwws.rwserver.controller.response;

import lombok.Data;

import java.util.List;

@Data
public class ListRegionResponse {
    private List<Region> regions = List.of();

    @Data
    public static class Region {
        private Integer id;
        private String chnName;
        private String engName;
        private String remark;
    }
}
