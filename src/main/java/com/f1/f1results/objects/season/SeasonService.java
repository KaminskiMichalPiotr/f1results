package com.f1.f1results.objects.season;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeasonService {

    SeasonRepository seasonRepository;

    @Autowired
    public SeasonService(SeasonRepository seasonRepository) {
        this.seasonRepository = seasonRepository;
    }

    public Season save(Season season) {
        return seasonRepository.save(season);
    }

    public Optional<Season> findById(Long id) {
        return seasonRepository.findById(id);
    }

    public Optional<Season> findSeasonByYear(int year) {
        return seasonRepository.findBySeasonYear(year);
    }

    public List<Season> findAll() {
        return seasonRepository.findAll();
    }
}
