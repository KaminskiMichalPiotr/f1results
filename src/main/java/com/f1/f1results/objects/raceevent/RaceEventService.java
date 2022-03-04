package com.f1.f1results.objects.raceevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<RaceEvent> getAll() {
        return raceEventRepository.findAll();
    }

    public Optional<RaceEvent> getById(Long raceId) {
        return this.raceEventRepository.findById(raceId);
    }

}
