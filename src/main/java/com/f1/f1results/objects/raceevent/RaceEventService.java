package com.f1.f1results.objects.raceevent;

import com.f1.f1results.exceptions.IncorrectParamException;
import com.f1.f1results.objects.location.Location;
import com.f1.f1results.objects.location.LocationService;
import com.f1.f1results.objects.season.Season;
import com.f1.f1results.objects.season.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RaceEventService {

    RaceEventRepository raceEventRepository;
    LocationService locationService;
    SeasonService seasonService;

    @Autowired
    public RaceEventService(RaceEventRepository raceEventRepository, LocationService locationService, SeasonService seasonService) {
        this.raceEventRepository = raceEventRepository;
        this.locationService = locationService;
        this.seasonService = seasonService;
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


    public RaceEvent createRaceEvent(int seasonYear, RaceEvent raceEvent) throws IncorrectParamException {
        Optional<Season> season = seasonService.findSeasonByYear(seasonYear);
        Optional<Location> location = locationService.findById(raceEvent.getLocation().getId());
        if (season.isPresent() && location.isPresent()) {
            boolean indexOccupied = season.get().getRaceEvents().stream().anyMatch(race -> race.getIndex() == raceEvent.getIndex());
            if (indexOccupied) {
                throw new IncorrectParamException("Race event index " + raceEvent.getIndex() + " already exists");
            } else {
                RaceEvent race = new RaceEvent(
                        null,
                        location.get(),
                        season.get(),
                        raceEvent.getDateOfRace(),
                        raceEvent.getIndex(),
                        Collections.emptyList()
                );
                return raceEventRepository.save(race);
            }
        }
        //TODO: throw error, check location id is present, check index not taken
        return null;
    }
}
