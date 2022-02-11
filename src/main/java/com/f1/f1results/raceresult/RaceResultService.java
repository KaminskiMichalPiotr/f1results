package com.f1.f1results.raceresult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RaceResultService {

    private RaceResultRepository raceResultRepository;

    @Autowired
    public RaceResultService(RaceResultRepository raceResultRepository) {
        this.raceResultRepository = raceResultRepository;
    }

    public RaceResult save(RaceResult raceResult) {
        return raceResultRepository.save(raceResult);
    }
}
