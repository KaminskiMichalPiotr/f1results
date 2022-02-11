package com.f1.f1results.objects.season;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
