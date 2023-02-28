package com.rwws.rwserver.service;


import com.rwws.rwserver.mapper.HobbyMapper;
import org.springframework.stereotype.Service;

@Service
public class HobbyService {
    private final HobbyMapper hobbyMapper;

    public HobbyService(HobbyMapper hobbyMapper) {
        this.hobbyMapper = hobbyMapper;
    }


}
