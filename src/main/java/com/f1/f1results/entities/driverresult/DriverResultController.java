package com.f1.f1results.entities.driverresult;

import com.f1.f1results.exceptions.IncorrectParamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping(path = "/create/{raceId}")
    public ResponseEntity<DriverResult> createDriverResult(@PathVariable Long raceId,
                                                           @Valid @RequestBody DriverResult driverResult) throws IncorrectParamException {
        DriverResult savedResult = driverResultService.createDriverResult(raceId, driverResult);
        return ResponseEntity.ok(savedResult);
    }
}
