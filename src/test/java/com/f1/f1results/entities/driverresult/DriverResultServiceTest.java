package com.f1.f1results.entities.driverresult;

import com.f1.f1results.entities.driver.Driver;
import com.f1.f1results.entities.driver.DriverService;
import com.f1.f1results.entities.raceevent.RaceEvent;
import com.f1.f1results.entities.raceevent.RaceEventService;
import com.f1.f1results.entities.team.Team;
import com.f1.f1results.entities.team.TeamService;
import com.f1.f1results.exceptions.IncorrectParamException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DriverResultServiceTest {

    private static final Long RACE_ID = 1L;
    private static final Long DRIVER_ID = 1L;
    private static final Long TEAM_ID = 1L;
    private static final int RACE_INDEX = 1;
    private static final int POSITION = 5;

    private DriverResultService underTest;

    @Mock
    private DriverResultRepository driverResultRepository;

    @Mock
    private RaceEventService raceEventService;

    @Mock
    private DriverService driverService;

    @Mock
    private TeamService teamService;

    private AutoCloseable autoCloseable;
    private Team team;
    private Driver driver;
    private DriverResult driverResult;
    private RaceEvent raceEvent;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new DriverResultService(
                driverResultRepository,
                raceEventService,
                driverService,
                teamService);
        mockData();
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canSave() {
        DriverResult driverResult = new DriverResult(null, null, 5, 5, null, 0, false);
        underTest.save(driverResult);
        verify(driverResultRepository).save(driverResult);
    }

    @Test
    void canGetDriverResultsByRace() {
        RaceEvent raceEvent = new RaceEvent();
        Long id = 1L;
        List<DriverResult> driverResultsByRace = underTest.getDriverResultsByRace(1L);
        assert (driverResultsByRace.equals(raceEvent.getDriverResults()));
    }

    @Test
    void canCreateDriverResult() throws IncorrectParamException {
        when(raceEventService.getById(RACE_ID)).thenReturn(Optional.of(raceEvent));
        when(driverService.getById(RACE_ID)).thenReturn(Optional.of(driver));
        when(teamService.getById(TEAM_ID)).thenReturn(Optional.of(team));
        when(driverResultRepository.save(any())).thenReturn(driverResult);
        underTest.createDriverResult(RACE_ID, driverResult);
    }

    @Test
    void cantCreateDriverResultWithWrongTeam() {
        when(raceEventService.getById(RACE_ID)).thenReturn(Optional.of(raceEvent));
        when(driverService.getById(RACE_ID)).thenReturn(Optional.of(driver));
        when(teamService.getById(TEAM_ID)).thenReturn(Optional.empty());
        when(driverResultRepository.save(any())).thenReturn(driverResult);
        assertThrows(IncorrectParamException.class, () -> underTest.createDriverResult(RACE_ID, driverResult));
    }

    @Test
    void cantCreateDriverResultWithWrongDriver() {
        when(raceEventService.getById(RACE_ID)).thenReturn(Optional.of(raceEvent));
        when(driverService.getById(RACE_ID)).thenReturn(Optional.empty());
        when(teamService.getById(TEAM_ID)).thenReturn(Optional.of(team));
        when(driverResultRepository.save(any())).thenReturn(driverResult);
        assertThrows(IncorrectParamException.class, () -> underTest.createDriverResult(RACE_ID, driverResult));
    }

    @Test
    void cantCreateDriverResultWithWrongRace() {
        when(raceEventService.getById(RACE_ID)).thenReturn(Optional.of(raceEvent));
        when(driverService.getById(RACE_ID)).thenReturn(Optional.of(driver));
        when(teamService.getById(TEAM_ID)).thenReturn(Optional.of(team));
        when(driverResultRepository.save(any())).thenReturn(driverResult);
        assertThrows(IncorrectParamException.class, () -> underTest.createDriverResult(RACE_ID - 1, driverResult));
    }

    //Position previously taken
    @Test
    void cantCreateDriverResultWithWrongPosition() {
        raceEvent.addDriverResult(new DriverResult(null, new Driver(), POSITION, 5, new Team(), 0, false));
        when(raceEventService.getById(RACE_ID)).thenReturn(Optional.of(raceEvent));
        when(driverService.getById(RACE_ID)).thenReturn(Optional.of(driver));
        when(teamService.getById(TEAM_ID)).thenReturn(Optional.of(team));
        when(driverResultRepository.save(any())).thenReturn(driverResult);
        assertThrows(IncorrectParamException.class, () -> underTest.createDriverResult(RACE_ID, driverResult));
    }

    //Driver already has result
    @Test
    void cantCreateDriverResultWithNotUniqueDriver() {
        raceEvent.addDriverResult(new DriverResult(null, driver, POSITION + 1, 5, new Team(), 0, false));
        when(raceEventService.getById(RACE_ID)).thenReturn(Optional.of(raceEvent));
        when(driverService.getById(RACE_ID)).thenReturn(Optional.of(driver));
        when(teamService.getById(TEAM_ID)).thenReturn(Optional.of(team));
        when(driverResultRepository.save(any())).thenReturn(driverResult);
        assertThrows(IncorrectParamException.class, () -> underTest.createDriverResult(RACE_ID, driverResult));
    }

    private void mockData() {
        team = new Team(
                TEAM_ID,
                "TeamName",
                "TAG",
                Collections.emptySet(),
                "Country");
        driver = new Driver(
                DRIVER_ID,
                "Driver",
                "05-05-1955",
                "Nationality",
                Collections.emptySet()
        );
        driverResult = new DriverResult(
                null,
                driver,
                POSITION,
                5,
                team,
                0,
                false
        );
        raceEvent = new RaceEvent(
                RACE_ID,
                null,
                null,
                "05-05-1955",
                RACE_INDEX,
                new ArrayList<>(),
                RaceDistance.FULL);

    }

}