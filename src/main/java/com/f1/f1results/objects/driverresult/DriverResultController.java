package com.f1.f1results.objects.driverresult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "driver-result")
public class DriverResultController {

    private final DriverResultService driverResultService;

    @Autowired
    public DriverResultController(DriverResultService driverResultService) {
        this.driverResultService = driverResultService;
    }

    @GetMapping(path = "/driver-results-by-race/{raceId}")
    public ResponseEntity<List<DriverResult>> getDriverResultsByRace(@PathVariable Long raceId) {
        return ResponseEntity.ok(driverResultService.getDriverResultsByRace(raceId));
    }
}
