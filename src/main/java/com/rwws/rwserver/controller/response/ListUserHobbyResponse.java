package com.rwws.rwserver.controller.response;

import lombok.Data;

import java.util.List;

@Data
public class ListUserHobbyResponse {

    private List<Hobby> hobbies = List.of();

    @Data
    public static class Hobby {
        private Long id;
        private String name;


    }
}
