package com.f1.f1results.entities.season;


import com.f1.f1results.exceptions.IncorrectParamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "season")
public class SeasonController {

    SeasonService seasonService;

    @Autowired
    public SeasonController(SeasonService seasonService) {
        this.seasonService = seasonService;
    }

    @GetMapping
    public ResponseEntity<List<Season>> getSeasons() {
        return ResponseEntity.ok(seasonService.findAll());
    }

    @PostMapping
    public ResponseEntity<Season> createSeason(@Valid @RequestBody Season season) throws IncorrectParamException {
        return ResponseEntity.ok(seasonService.createSeason(season));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Season> getSeasonById(@PathVariable(value = "id") Long id) {
        Optional<Season> season = seasonService.findById(id);
        return season.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(path = "seasonsYears")
    public ResponseEntity<List<Integer>> getSeasonsYears() {
        List<Season> seasons = seasonService.findAll();
        List<Integer> seasonsYears = seasons.stream()
                .map(Season::getSeasonYear)
                .sorted()
                .collect(Collectors.toList());
        return ResponseEntity.ok(seasonsYears);
    }

    @GetMapping(path = "seasonResult/{year}")
    public ResponseEntity<SeasonResultDto> getSeasonResult(@PathVariable(value = "year") int year) {
        Optional<Season> seasonByYear = seasonService.findSeasonByYear(year);
        if (seasonByYear.isPresent()) {
            SeasonResultDto seasonResultDto = SeasonResultDto.fromSeason(seasonByYear.get());
            return ResponseEntity.ok(seasonResultDto);
        } else
            return ResponseEntity.notFound().build();

    }


    @GetMapping(path = "seasonTeamResult/{year}")
    public ResponseEntity<SeasonTeamResultDto> getSeasonTeamResult(@PathVariable(value = "year") int year) {
        Optional<Season> seasonByYear = seasonService.findSeasonByYear(year);
        if (seasonByYear.isPresent()) {
            SeasonTeamResultDto seasonTeamResultDto = SeasonTeamResultDto.fromSeason(seasonByYear.get());
            return ResponseEntity.ok(seasonTeamResultDto);
        } else
            return ResponseEntity.notFound().build();

    }


}
