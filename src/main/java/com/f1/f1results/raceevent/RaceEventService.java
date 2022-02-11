package com.f1.f1results.raceevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RaceEventService {

    RaceEventRepository raceEventRepository;

    @Autowired
    public RaceEventService(RaceEventRepository raceEventRepository) {
        this.raceEventRepository = raceEventRepository;
    }

    public RaceEvent save(RaceEvent raceEvent) {
        return raceEventRepository.save(raceEvent);
    }

}
