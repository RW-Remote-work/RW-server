package com.rwws.rwserver.controller;

import com.rwws.rwserver.controller.response.ListHobbyResponse;
import com.rwws.rwserver.service.HobbyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hobbies")
public class HobbyController {
    private final HobbyService hobbyService;

    public HobbyController(HobbyService hobbyService) {
        this.hobbyService = hobbyService;
    }

    public ListHobbyResponse listHobby() {
        return hobbyService.listHobby();
    }
}
