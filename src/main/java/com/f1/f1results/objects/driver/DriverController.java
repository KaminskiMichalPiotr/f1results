package com.f1.f1results.objects.driver;

import com.f1.f1results.objects.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "driver")
public class DriverController {


    DriverService driverService;
    TeamService teamService;

    @Autowired
    public DriverController(DriverService driverService, TeamService teamService) {
        this.driverService = driverService;
        this.teamService = teamService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<Driver>> getDrivers() {
        return new ResponseEntity<>(driverService.getDrivers(), HttpStatus.OK);
    }

    @PostMapping("/saveAll")
    public ResponseEntity<List<Driver>> saveDrivers(List<Driver> drivers) {
        return new ResponseEntity<>(driverService.saveDrivers(drivers), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Driver> saveDriver(@Valid @RequestBody Driver driver) {
        Driver save = driverService.save(driver);
        teamService.addDriverToTeams(save, driver.getTeams());
        return ResponseEntity.status(HttpStatus.OK).body(save);
    }
}
