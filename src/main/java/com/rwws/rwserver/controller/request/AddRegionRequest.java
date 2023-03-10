package com.rwws.rwserver.controller.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class AddRegionRequest {
    @Valid
    private List<Region> region = List.of();

    @Data
    public static class Region {
        @NotBlank
        private String chnName;
        @NotBlank
        private String engName;
        private String remark;
    }
}
