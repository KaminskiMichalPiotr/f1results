package com.f1.f1results.objects.raceevent;

import com.f1.f1results.objects.season.Season;
import com.f1.f1results.objects.season.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
