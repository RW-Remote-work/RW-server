package com.rwws.rwserver.controller.request.job;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class AddJobClassRequest {
    @Valid
    private List<JobClass> jobClasses = List.of();

    @Data
    public static class JobClass {
        @NotBlank
        private String chnName;
        @NotBlank
        private String engName;
        private String remark;
    }
}
