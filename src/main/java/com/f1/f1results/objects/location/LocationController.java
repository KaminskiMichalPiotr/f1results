package com.f1.f1results.objects.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "location")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<List<Location>> getLocations() {
        return ResponseEntity.ok(locationService.getLocations());
    }

    @PostMapping
    public ResponseEntity<Location> saveLocation(@Valid @RequestBody Location location) {
        return ResponseEntity.ok(locationService.save(location));
    }

}
