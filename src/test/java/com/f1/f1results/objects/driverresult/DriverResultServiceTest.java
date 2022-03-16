package com.f1.f1results.objects.driverresult;

import com.f1.f1results.exceptions.IncorrectParamException;
import com.f1.f1results.objects.driver.Driver;
import com.f1.f1results.objects.driver.DriverService;
import com.f1.f1results.objects.raceevent.RaceEvent;
import com.f1.f1results.objects.raceevent.RaceEventService;
import com.f1.f1results.objects.team.Team;
import com.f1.f1results.objects.team.TeamService;
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

    private final Long raceId = 1L;
    private final Long driverId = 1L;
    private final Long teamId = 1L;
    private final int raceIndex = 1;
    private final int position = 5;
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
        DriverResult driverResult = new DriverResult(null, null, 5, 5, null);
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
        when(raceEventService.getById(raceId)).thenReturn(Optional.of(raceEvent));
        when(driverService.getById(raceId)).thenReturn(Optional.of(driver));
        when(teamService.getById(teamId)).thenReturn(Optional.of(team));
        when(driverResultRepository.save(any())).thenReturn(driverResult);
        underTest.createDriverResult(raceId, driverResult);
    }

    @Test
    void cantCreateDriverResultWithWrongTeam() {
        when(raceEventService.getById(raceId)).thenReturn(Optional.of(raceEvent));
        when(driverService.getById(raceId)).thenReturn(Optional.of(driver));
        when(teamService.getById(teamId)).thenReturn(Optional.empty());
        when(driverResultRepository.save(any())).thenReturn(driverResult);
        assertThrows(IncorrectParamException.class, () -> underTest.createDriverResult(raceId, driverResult));
    }

    @Test
    void cantCreateDriverResultWithWrongDriver() {
        when(raceEventService.getById(raceId)).thenReturn(Optional.of(raceEvent));
        when(driverService.getById(raceId)).thenReturn(Optional.empty());
        when(teamService.getById(teamId)).thenReturn(Optional.of(team));
        when(driverResultRepository.save(any())).thenReturn(driverResult);
        assertThrows(IncorrectParamException.class, () -> underTest.createDriverResult(raceId, driverResult));
    }

    @Test
    void cantCreateDriverResultWithWrongRace() {
        when(raceEventService.getById(raceId)).thenReturn(Optional.of(raceEvent));
        when(driverService.getById(raceId)).thenReturn(Optional.of(driver));
        when(teamService.getById(teamId)).thenReturn(Optional.of(team));
        when(driverResultRepository.save(any())).thenReturn(driverResult);
        assertThrows(IncorrectParamException.class, () -> underTest.createDriverResult(raceId - 1, driverResult));
    }

    //Position previously taken
    @Test
    void cantCreateDriverResultWithWrongPosition() {
        raceEvent.addDriverResult(new DriverResult(null, new Driver(), position, 5, new Team()));
        when(raceEventService.getById(raceId)).thenReturn(Optional.of(raceEvent));
        when(driverService.getById(raceId)).thenReturn(Optional.of(driver));
        when(teamService.getById(teamId)).thenReturn(Optional.of(team));
        when(driverResultRepository.save(any())).thenReturn(driverResult);
        assertThrows(IncorrectParamException.class, () -> underTest.createDriverResult(raceId, driverResult));
    }

    //Driver already has result
    @Test
    void cantCreateDriverResultWithNotUniqueDriver() {
        raceEvent.addDriverResult(new DriverResult(null, driver, position + 1, 5, new Team()));
        when(raceEventService.getById(raceId)).thenReturn(Optional.of(raceEvent));
        when(driverService.getById(raceId)).thenReturn(Optional.of(driver));
        when(teamService.getById(teamId)).thenReturn(Optional.of(team));
        when(driverResultRepository.save(any())).thenReturn(driverResult);
        assertThrows(IncorrectParamException.class, () -> underTest.createDriverResult(raceId, driverResult));
    }

    private void mockData() {
        team = new Team(
                teamId,
                "TeamName",
                "TAG",
                Collections.emptySet(),
                "Country");
        driver = new Driver(
                driverId,
                "Driver",
                "05-05-1955",
                "Nationality",
                Collections.emptySet()
        );
        driverResult = new DriverResult(
                null,
                driver,
                position,
                5,
                team
        );
        raceEvent = new RaceEvent(
                raceId,
                null,
                null,
                "05-05-1955",
                raceIndex,
                new ArrayList<>());

    }

}