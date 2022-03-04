package com.f1.f1results.objects.raceresult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaceResultService {

    private final RaceResultRepository raceResultRepository;

    @Autowired
    public RaceResultService(RaceResultRepository raceResultRepository) {
        this.raceResultRepository = raceResultRepository;
    }

    public RaceResult save(RaceResult raceResult) {
        return raceResultRepository.save(raceResult);
    }

    public List<RaceResult> getAll() {
        return raceResultRepository.findAll();
    }
}
