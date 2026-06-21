package com.example.playtesttracker.repository;

import com.example.playtesttracker.entity.Platform;
import com.example.playtesttracker.entity.Playtest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface PlaytestRepository extends JpaRepository<Playtest, Long> {

    List<Playtest> findByGameTitleContainingIgnoreCase(String gameTitle);

    List<Playtest> findByGameTitleContainingIgnoreCase(String gameTitle, Sort sort);

    List<Playtest> findByPlatform(Platform platform);

    long countByPlatform_Id(Long platformId);


}
