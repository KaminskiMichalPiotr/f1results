package com.f1.f1results.objects.season;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{id}")
    public ResponseEntity<Season> getSeasonById(@PathVariable(value = "id") Long id) {
        Optional<Season> season = seasonService.findById(id);
        if (season.isPresent()) {
            return ResponseEntity.ok(season.get());
        } else {
            return ResponseEntity.notFound().build();
        }
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

}
