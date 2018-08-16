package com.example.minichat.service.impl;

import com.example.minichat.service.MatchMakerService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author by guanda
 * @Date 2018/8/6 16:15
 */
@Service("matchMakerService")
public class MatchMakerServiceImpl implements MatchMakerService {

    private ConcurrentHashMap<Long, String> onlineMatchMakerMap = new ConcurrentHashMap<>();

    @Override
    public void addMatchMakerOnline(Long mid, String socket) {
        onlineMatchMakerMap.put(mid, socket);
    }

    @Override
    public Map<Long, String> getMatchMakerOnline() {
        return onlineMatchMakerMap;
    }

    @Override
    public void deleteMatchMakerOnline(Long mid) {
        onlineMatchMakerMap.remove(mid);
    }

}
