package com.rwws.rwserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zalando.problem.AbstractThrowableProblem;

@RestController
@RequestMapping("/hobbies")
public class HobbyController {

    @GetMapping
    public void addHobby() {
        throw new AbstractThrowableProblem() {

        };
    }
}
