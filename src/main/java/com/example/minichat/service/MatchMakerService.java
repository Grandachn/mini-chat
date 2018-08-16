package com.example.minichat.service;

import java.util.Map;

/**
 * @Author by guanda
 * @Date 2018/8/6 16:14
 */
public interface MatchMakerService {
    void addMatchMakerOnline(Long mid,String socket);
    Map<Long, String> getMatchMakerOnline();
    void deleteMatchMakerOnline(Long mid);
}
