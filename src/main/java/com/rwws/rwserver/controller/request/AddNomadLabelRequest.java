package com.rwws.rwserver.controller.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class AddNomadLabelRequest {
    @Valid
    private List<NomadLabel> nomadLabels = List.of();

    @Data
    public static class NomadLabel {
        private Long id;
        @NotBlank
        private String labelContent;
        private String remark;
    }
}
