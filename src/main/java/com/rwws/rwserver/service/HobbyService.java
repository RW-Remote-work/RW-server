package com.rwws.rwserver.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.rwws.rwserver.controller.response.ListHobbyResponse;
import com.rwws.rwserver.mapper.HobbyMapper;
import org.springframework.stereotype.Service;

@Service
public class HobbyService {
    private final HobbyMapper hobbyMapper;

    public HobbyService(HobbyMapper hobbyMapper) {
        this.hobbyMapper = hobbyMapper;
    }

    public ListHobbyResponse listHobby() {
        var hobbies = hobbyMapper.selectList(Wrappers.emptyWrapper()).stream()
                .map(it -> {
                    var hobby = new ListHobbyResponse.Hobby();
                    hobby.setId(it.getId());
                    hobby.setName(it.getName());
                    return hobby;
                }).toList();
        var response = new ListHobbyResponse();
        response.setHobbies(hobbies);
        return response;
    }
}
