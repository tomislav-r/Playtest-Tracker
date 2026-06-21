package com.example.playtesttracker.service;

import com.example.playtesttracker.entity.Platform;

import java.util.List;

public interface PlatformService {

    List<Platform> findAll();

    Platform findById(Long id);

    Platform create(Platform platform);

    Platform update(Long id, Platform platform);

    void delete(Long id);

    long countPlaytests(Long platformId);
}
