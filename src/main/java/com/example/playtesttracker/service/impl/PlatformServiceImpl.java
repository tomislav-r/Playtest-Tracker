package com.example.playtesttracker.service.impl;

import com.example.playtesttracker.entity.Platform;
import com.example.playtesttracker.repository.PlatformRepository;
import com.example.playtesttracker.repository.PlaytestRepository;
import com.example.playtesttracker.service.PlatformService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatformServiceImpl implements PlatformService {

    private final PlatformRepository platformRepository;
    private final PlaytestRepository playtestRepository;

    public PlatformServiceImpl(PlatformRepository platformRepository, PlaytestRepository playtestRepository) {

        this.platformRepository = platformRepository;
        this.playtestRepository = playtestRepository;
    }

    @Override
    public List<Platform> findAll()
    {
        return platformRepository.findAll();
    }

    @Override
    public Platform findById(Long id)
    {
        return platformRepository.findById(id).orElseThrow( () -> new RuntimeException( "Platform not found" ) );
    }

    @Override
    public Platform create(Platform platform)
    {
        return platformRepository.save(platform);
    }

    @Override
    public Platform update(Long id, Platform platform)
    {
        Platform existingPlatform = findById(id);

        existingPlatform.setName(platform.getName());
        existingPlatform.setWebsite(platform.getWebsite());
        existingPlatform.setDescription(platform.getDescription());

        return platformRepository.save(existingPlatform);
    }

    @Override
    public void delete(Long id) {
        if (countPlaytests(id) > 0) {
            throw new RuntimeException("Platforma se ne može obrisati jer ima povezane playtestove.");
        }

        platformRepository.deleteById(id);
    }

    @Override
    public long countPlaytests(Long platformId) {
        return playtestRepository.countByPlatform_Id(platformId);
    }

}
