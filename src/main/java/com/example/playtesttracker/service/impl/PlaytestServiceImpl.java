package com.example.playtesttracker.service.impl;

import com.example.playtesttracker.dto.PlaytestRequest;
import com.example.playtesttracker.entity.Platform;
import com.example.playtesttracker.entity.Playtest;
import com.example.playtesttracker.repository.PlatformRepository;
import com.example.playtesttracker.repository.PlaytestRepository;
import com.example.playtesttracker.service.PlaytestService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaytestServiceImpl implements PlaytestService {

    private final PlaytestRepository playtestRepository;
    private final PlatformRepository platformRepository;

    public PlaytestServiceImpl(PlaytestRepository playtestRepository, PlatformRepository platformRepository) {
        this.playtestRepository = playtestRepository;
        this.platformRepository = platformRepository;
    }

    @Override
    public List<Playtest> findAll() {
        return playtestRepository.findAll();
    }

    @Override
    public Playtest findById(Long id) {
        return playtestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Playtest not found"));
    }

    @Override
    public Playtest create(PlaytestRequest playtestRequest) {
        Platform platform = platformRepository.findById(playtestRequest.getPlatformId())
                .orElseThrow(() -> new RuntimeException("Platform not found"));

        Playtest playtest = new Playtest();

        playtest.setGameTitle(playtestRequest.getGameTitle());
        playtest.setTestDate(playtestRequest.getTestDate());
        playtest.setTestType(playtestRequest.getTestType());
        playtest.setStatus(playtestRequest.getStatus());
        playtest.setRating(playtestRequest.getRating());
        playtest.setNotes(playtestRequest.getNotes());
        playtest.setPlatform(platform);

        return playtestRepository.save(playtest);
    }

    @Override
    public Playtest update(Long id, PlaytestRequest playtestRequest) {
        Playtest playtest = findById(id);

        Platform platform = platformRepository.findById(playtestRequest.getPlatformId())
                .orElseThrow(() -> new RuntimeException("Platform not found"));

        playtest.setGameTitle(playtestRequest.getGameTitle());
        playtest.setTestDate(playtestRequest.getTestDate());
        playtest.setTestType(playtestRequest.getTestType());
        playtest.setStatus(playtestRequest.getStatus());
        playtest.setRating(playtestRequest.getRating());
        playtest.setNotes(playtestRequest.getNotes());
        playtest.setPlatform(platform);

        return playtestRepository.save(playtest);
    }

    @Override
    public List<Playtest> searchByGameTitle(String gameTitle) {
        return playtestRepository.findByGameTitleContainingIgnoreCase(gameTitle);
    }

    @Override
    public List<Playtest> findByPlatform(Platform platform) {
        return playtestRepository.findByPlatform(platform);
    }

    @Override
    public List<Playtest> findAllSorted(String sortBy, String direction) {
        return playtestRepository.findAll(buildSort(sortBy, direction));
    }

    @Override
    public List<Playtest> searchByGameTitleSorted(String gameTitle, String sortBy, String direction) {
        return playtestRepository.findByGameTitleContainingIgnoreCase(gameTitle, buildSort(sortBy, direction));
    }

    private Sort buildSort(String sortBy, String direction) {
        String fieldName = switch (sortBy) {
            case "gameTitle" -> "gameTitle";
            case "rating" -> "rating";
            case "status" -> "status";
            default -> "testDate";
        };

        Sort.Direction sortDirection = "asc".equalsIgnoreCase(direction)
                ? Sort.Direction.ASC
                : Sort.Direction.DESC;

        return Sort.by(sortDirection, fieldName);
    }

    @Override
    public void delete(Long id) {
        playtestRepository.deleteById(id);
    }
}