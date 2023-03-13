package com.rwws.rwserver.service;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.rwws.rwserver.controller.request.AddHobbyRequest;
import com.rwws.rwserver.controller.response.ListUserHobbyResponse;
import com.rwws.rwserver.domain.Hobby;
import com.rwws.rwserver.domain.UserHobby;
import com.rwws.rwserver.mapper.HobbyMapper;
import com.rwws.rwserver.mapper.UserHobbyMapper;
import org.springframework.stereotype.Service;

@Service
public class UserHobbyService {
    private final UserHobbyMapper userHobbyMapper;
    private final HobbyMapper hobbyMapper;

    public UserHobbyService(UserHobbyMapper userHobbyMapper,
                            HobbyMapper hobbyMapper) {
        this.userHobbyMapper = userHobbyMapper;
        this.hobbyMapper = hobbyMapper;
    }

    public void addUserHobby(Long userId, AddHobbyRequest request) {
        var hobby = hobbyMapper.selectOne(
                Wrappers.<Hobby>lambdaQuery()
                        .eq(Hobby::getName, request.getName())
        );
        if (hobby == null) {
            hobby = new Hobby();
            hobby.setName(request.getName());
            hobbyMapper.insert(hobby);
        }

        var userHobby = new UserHobby(hobby.getId(), userId);
        userHobbyMapper.insert(userHobby);
    }

    public ListUserHobbyResponse listUserHobby(Long userId) {
        var hobbyIds = userHobbyMapper.selectList(
                Wrappers.<UserHobby>lambdaQuery()
                        .eq(UserHobby::getUserId, userId)
        ).stream().map(UserHobby::getHobbyId).toList();

        var hobbies = hobbyMapper.selectList(
                Wrappers.<Hobby>lambdaQuery()
                        .in(Hobby::getId, hobbyIds)
        ).stream().map(it -> {
            var hobby = new ListUserHobbyResponse.Hobby();
            hobby.setId(it.getId());
            hobby.setName(it.getName());
            return hobby;
        }).toList();

        var response = new ListUserHobbyResponse();
        response.setHobbies(hobbies);
        return response;
    }

    public void deleteUserHobby(Long userId, Long hobbyId) {
        userHobbyMapper.delete(
                Wrappers.<UserHobby>lambdaQuery()
                        .eq(UserHobby::getUserId, userId)
                        .eq(UserHobby::getHobbyId, hobbyId)
        );
    }

}
