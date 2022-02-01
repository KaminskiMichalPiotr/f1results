package com.f1.f1results;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.f1.f1results.raceresult.PointsScoringSystem.getPointsScoredByPosition;

@RestController
@RequestMapping(path = "test")
public class TestController {

    @GetMapping
    public Double getTest() {
        return getPointsScoredByPosition(1, true);
    }

}
