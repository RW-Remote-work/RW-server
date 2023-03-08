package com.rwws.rwserver.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rwws.rwserver.controller.request.AddHobbyRequest;
import com.rwws.rwserver.controller.response.ListHobbyResponse;
import com.rwws.rwserver.domain.Hobby;
import com.rwws.rwserver.mapper.HobbyMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HobbyService {
    private final HobbyMapper hobbyMapper;

    public HobbyService(HobbyMapper hobbyMapper) {
        this.hobbyMapper = hobbyMapper;
    }

    public void addHobby(Long userId, AddHobbyRequest request) {
        var hobby = new Hobby();
        hobby.setName(request.getName());
        hobby.setUserId(userId);
        hobbyMapper.insert(hobby);
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

    public void deleteHobby(Long userId, List<Long> ids) {
        hobbyMapper.delete(
                new QueryWrapper<Hobby>()
                        .eq("user_id", userId)
                        .in("id", ids)
        );
    }

    public boolean exist(Long userId, String name) {
        return hobbyMapper.exists(
                new QueryWrapper<Hobby>()
                        .eq("user_id", userId)
                        .eq("name", name)
        );
    }

}
