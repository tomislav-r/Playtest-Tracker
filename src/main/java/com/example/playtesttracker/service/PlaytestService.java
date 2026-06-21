package com.example.playtesttracker.service;

import com.example.playtesttracker.dto.PlaytestRequest;
import com.example.playtesttracker.entity.Platform;
import com.example.playtesttracker.entity.Playtest;

import java.util.List;

public interface PlaytestService {

    List<Playtest> findAll();

    Playtest findById(Long id);

    Playtest create(PlaytestRequest playtestRequest);

    List<Playtest> findAllSorted(String sortBy, String direction);

    List<Playtest> searchByGameTitleSorted(String gameTitle, String sortBy, String direction);

    Playtest update(Long id, PlaytestRequest playtestRequest);

    void delete(Long id);

    List<Playtest> searchByGameTitle(String gameTitle);

    List<Playtest> findByPlatform(Platform platform);


}
