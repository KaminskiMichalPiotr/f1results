package com.f1.f1results.entities.raceevent;

import com.f1.f1results.entities.season.Season;
import com.f1.f1results.entities.season.SeasonService;
import com.f1.f1results.exceptions.IncorrectParamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "race-event")
public class RaceEventController {

    private final RaceEventService raceEventService;
    private final SeasonService seasonService;

    @Autowired
    public RaceEventController(RaceEventService raceEventService, SeasonService seasonService) {
        this.raceEventService = raceEventService;
        this.seasonService = seasonService;
    }

    @GetMapping()
    public ResponseEntity<List<RaceEvent>> getRaceEvents() {
        return ResponseEntity.ok(raceEventService.getAll());
    }

    @SuppressWarnings("OptionalIsPresent")
    @GetMapping(path = "by-season/{seasonYear}")
    public ResponseEntity<List<RaceEvent>> getRaceEventsBySeasonYear(@PathVariable int seasonYear) {
        Optional<Season> seasonByYear = seasonService.findSeasonByYear(seasonYear);
        if (seasonByYear.isPresent()) {
            return ResponseEntity.ok(seasonByYear.get().getRaceEvents());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "{seasonYear}")
    public ResponseEntity<RaceEvent> createRaceEvent(@PathVariable int seasonYear,
                                                     @RequestBody RaceEvent raceEvent) throws IncorrectParamException {
        if (raceEvent.getId() == null) {
            RaceEvent savedRaceEvent = raceEventService.createRaceEvent(seasonYear, raceEvent);
            return ResponseEntity.ok(savedRaceEvent);
        }
        return ResponseEntity.badRequest().build();
    }

}
