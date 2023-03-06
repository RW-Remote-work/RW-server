package com.rwws.rwserver.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rwws.rwserver.controller.response.ListHobbyResponse;
import com.rwws.rwserver.domain.Hobby;
import com.rwws.rwserver.mapper.HobbyMapper;
import org.springframework.stereotype.Service;

@Service
public class HobbyService {
    private final HobbyMapper hobbyMapper;

    public HobbyService(HobbyMapper hobbyMapper) {
        this.hobbyMapper = hobbyMapper;
    }

    public void addHobby() {

    }

    public ListHobbyResponse listHobby(Long userId) {
        var hobbies = hobbyMapper.selectList(
                new QueryWrapper<Hobby>()
                        .eq("user_id", userId)
        ).stream().map(it -> {
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
