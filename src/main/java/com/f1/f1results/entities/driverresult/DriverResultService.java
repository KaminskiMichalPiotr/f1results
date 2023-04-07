package com.f1.f1results.entities.driverresult;

import com.f1.f1results.entities.driver.Driver;
import com.f1.f1results.entities.driver.DriverService;
import com.f1.f1results.entities.raceevent.RaceEvent;
import com.f1.f1results.entities.raceevent.RaceEventService;
import com.f1.f1results.entities.team.Team;
import com.f1.f1results.entities.team.TeamService;
import com.f1.f1results.exceptions.IncorrectParamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.f1.f1results.entities.driverresult.PointsScoringSystem.getPointsScoredByPosition;

@Service
public class DriverResultService {

    private final DriverResultRepository driverResultRepository;
    private final RaceEventService raceEventService;
    private final DriverService driverService;
    private final TeamService teamService;

    @Autowired
    public DriverResultService(DriverResultRepository driverResultRepository,
                               RaceEventService raceEventService,
                               DriverService driverService,
                               TeamService teamService) {
        this.driverResultRepository = driverResultRepository;
        this.raceEventService = raceEventService;
        this.driverService = driverService;
        this.teamService = teamService;
    }

    public DriverResult save(DriverResult driverResult) {
        return driverResultRepository.save(driverResult);
    }

    public List<DriverResult> getDriverResultsByRace(Long raceId) {
        Optional<RaceEvent> raceEvent = this.raceEventService.getById(raceId);
        if (raceEvent.isPresent()) {
            return raceEvent.get().getDriverResults();
        }
        return Collections.emptyList();
    }

    public DriverResult createDriverResult(Long raceId, DriverResult driverResult) throws IncorrectParamException {
        RaceEvent raceEvent = getRace(raceId);
        verifyUniquePositionAndUniqueDriver(driverResult, raceEvent);
        Driver driver = getDriver(driverResult);
        Team team = getTeam(driverResult);

        DriverResult resultToSave = new DriverResult(
                null,
                driver,
                driverResult.getPosition(),
                getPointsScoredByPosition(driverResult.getPosition(), RaceDistance.FULL),
                team,
                driverResult.getSprintRacePosition(),
                driverResult.hasFastestLap());
        resultToSave = driverResultRepository.save(resultToSave);
        raceEvent.addDriverResult(resultToSave);
        raceEventService.save(raceEvent);
        return resultToSave;
    }

    //Check if driver result exist in race event, check if position exists
    private void verifyUniquePositionAndUniqueDriver(DriverResult driverResult, RaceEvent raceEvent) throws IncorrectParamException {
        List<DriverResult> driverResults = raceEvent.getDriverResults();
        if (driverResults.stream().anyMatch(result -> result.getPosition() == driverResult.getPosition()))
            throw new IncorrectParamException("Position " + driverResult.getPosition() + " is already taken!");
        if (driverResults.stream().anyMatch(result -> Objects.equals(result.getDriver().getId(), driverResult.getDriver().getId())))
            throw new IncorrectParamException("Driver " + driverResult.getDriver().getName() + " already has result in the race!");
    }

    private Team getTeam(DriverResult driverResult) throws IncorrectParamException {
        Optional<Team> team = teamService.getById(driverResult.getTeam().getId());
        if (team.isEmpty())
            throw new IncorrectParamException("Team id:" + driverResult.getTeam().getId() + " is incorrect!");
        return team.get();
    }

    private Driver getDriver(DriverResult driverResult) throws IncorrectParamException {
        Optional<Driver> driver = driverService.getById(driverResult.getDriver().getId());
        if (driver.isEmpty())
            throw new IncorrectParamException("Driver id:" + driverResult.getDriver().getId() + " is incorrect!");
        return driver.get();
    }

    private RaceEvent getRace(Long id) throws IncorrectParamException {
        Optional<RaceEvent> raceEvent = raceEventService.getById(id);
        if (raceEvent.isEmpty())
            throw new IncorrectParamException("Race id is incorrect");
        return raceEvent.get();
    }

    public List<DriverResult> getDriverResultByDriver(Driver driver) {
        return driverResultRepository.findDriverResultsByDriver(driver);
    }

    public void deleteDriverResults(List<DriverResult> driverResults) {
        driverResultRepository.deleteAll(driverResults);
    }

    public List<DriverResult> getDriverResults() {
        return driverResultRepository.findAll();
    }
}
