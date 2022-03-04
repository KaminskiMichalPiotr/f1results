package com.f1.f1results.objects.raceresult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "race-result")
public class RaceResultController {

    private final RaceResultService raceResultService;

    @Autowired
    public RaceResultController(RaceResultService raceResultService) {
        this.raceResultService = raceResultService;
    }

    @GetMapping()
    public ResponseEntity<List<RaceResult>> getRaceResults() {
        return ResponseEntity.ok(raceResultService.getAll());
    }
}
