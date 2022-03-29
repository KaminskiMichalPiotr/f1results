package com.f1.f1results.dataloader;

import com.f1.f1results.entities.driver.Driver;
import com.f1.f1results.entities.driver.DriverService;
import com.f1.f1results.entities.driverresult.DriverResult;
import com.f1.f1results.entities.driverresult.DriverResultService;
import com.f1.f1results.entities.driverresult.RaceDistance;
import com.f1.f1results.entities.location.Location;
import com.f1.f1results.entities.location.LocationService;
import com.f1.f1results.entities.raceevent.RaceEvent;
import com.f1.f1results.entities.raceevent.RaceEventService;
import com.f1.f1results.entities.season.Season;
import com.f1.f1results.entities.season.SeasonService;
import com.f1.f1results.entities.team.Team;
import com.f1.f1results.entities.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

import static com.f1.f1results.entities.driverresult.PointsScoringSystem.getPointsScoredByPosition;

@Component
public class DataLoader implements CommandLineRunner {

    private static final int LOCATIONS_STARTS_AT = 6;
    private static final int DRIVER_STARTS_AT = 1;
    private static final int TEAM_STARTS_AT = 3;
    private static final int RESULT_STARTS_AT_X = 6;
    private static final int RESULT_STARTS_AT_Y = 3;
    @Autowired
    LocationService locationService;
    @Autowired
    DriverService driverService;
    @Autowired
    TeamService teamService;
    @Autowired
    SeasonService seasonService;
    @Autowired
    RaceEventService raceEventService;
    @Autowired
    DriverResultService driverResultService;
    @Autowired
    ResourcePatternResolver resourcePatternResolver;
    private String fileName = "2021.csv";


    @Override
    public void run(String... args) throws Exception {
        Resource[] resources = resourcePatternResolver.getResources("classpath:data/*.csv");
        //readData();
    }

    public void readData() {

        List<List<String>> records = new ArrayList<>();
        readDataFromFile(records);
        List<Location> locations = extractOrCreateLocations(records);
        List<Driver> drivers = new ArrayList<>();
        List<Team> teams = new ArrayList<>();
        extractOrCreateDriversAndTeams(drivers, teams, records);


        Season season = createSeason(2021);
        List<RaceEvent> raceEvents = createRaceEvents(locations, season);
        createDriverResults(raceEvents, records, drivers, teams);

    }

    private void createDriverResults(List<RaceEvent> raceEvents,
                                     List<List<String>> records,
                                     List<Driver> drivers,
                                     List<Team> teams) {
        List<List<String>> results = new ArrayList<>(records.subList(RESULT_STARTS_AT_Y, records.size()));
        for (int i = 0; i < results.size(); i++) {
            results.set(i, results.get(i).subList(RESULT_STARTS_AT_X, results.get(i).size() - 1));
        }
        //loop through driver
        for (int i = 0; i < drivers.size(); i++) {
            List<String> row = results.get(i);
            for (int j = 0; j < row.size(); j++) {
                int position;
                try {
                    position = Integer.parseInt(row.get(j));
                } catch (NumberFormatException e) {
                    position = -1;
                }
                if (position != -1) {
                    DriverResult driverResult = new DriverResult(
                            null,
                            drivers.get(i),
                            position,
                            getPointsScoredByPosition(position, RaceDistance.FULL),
                            teams.get(i)
                    );
                    DriverResult save = driverResultService.save(driverResult);
                    raceEvents.get(j).addDriverResult(save);
                    raceEventService.save(raceEvents.get(j));
                }

            }

        }
    }

    private List<RaceEvent> createRaceEvents(List<Location> locations, Season season) {
        List<RaceEvent> raceEvents = new ArrayList<>();
        for (int i = 0; i < locations.size(); i++) {
            //TODO: Add race date to excel and read
            RaceEvent raceEvent = new RaceEvent(
                    null,
                    locations.get(i),
                    season,
                    "20-02-2020",
                    i + 1,
                    new ArrayList<>()
            );
            raceEvents.add(raceEventService.save(raceEvent));
        }
        return raceEvents;
    }

    private Season createSeason(int year) {
        Season season = new Season();
        season.setSeasonYear(year);
        return seasonService.save(season);
    }

    private void extractOrCreateDriversAndTeams(List<Driver> drivers, List<Team> teams, List<List<String>> records) {
        extractTeams(teams, records);
        extractDrivers(drivers, records);
        addDriversToTeams(teams, drivers, records);
    }

    @Transactional
    void addDriversToTeams(List<Team> teams, List<Driver> drivers, List<List<String>> records) {
        for (int i = 0; i < teams.size(); i++) {
            teams.get(i).addDriver(drivers.get(i));
            teams.set(i, teamService.save(teams.get(i)));
        }

    }

    private void extractTeams(List<Team> teams, List<List<String>> records) {
        List<List<String>> teamRecords = new ArrayList<>(records.subList(3, records.size()));
        for (int i = 0; i < teamRecords.size(); i++) {
            teamRecords.set(i, teamRecords.get(i).subList(TEAM_STARTS_AT, TEAM_STARTS_AT + 3));
        }
        teamRecords.forEach(
                row -> {
                    String teamName = row.get(0);
                    String teamTag = row.get(1);
                    String country = row.get(2);
                    Optional<Team> team = teamService.findByTag(teamTag);
                    if (team.isPresent()) {
                        teams.add(team.get());
                    } else {
                        //create driver
                        Team teamToSave = new Team(
                                null,
                                teamName,
                                teamTag,
                                new HashSet<>(),
                                country);
                        teamToSave = teamService.save(teamToSave);
                        teams.add(teamToSave);
                    }
                }
        );
    }

    private void extractDrivers(List<Driver> drivers, List<List<String>> records) {
        List<List<String>> driversRecords = new ArrayList<>(records.subList(3, records.size()));
        for (int i = 0; i < driversRecords.size(); i++) {
            driversRecords.set(i, driversRecords.get(i).subList(DRIVER_STARTS_AT, DRIVER_STARTS_AT + 2));
        }
        driversRecords.forEach(
                row -> {
                    String country = row.get(0);
                    String name = row.get(1);
                    Optional<Driver> driver = driverService.findByName(name);
                    if (driver.isPresent()) {
                        drivers.add(driver.get());
                    } else {
                        //create driver
                        Driver toSave = Driver.builder()
                                .driver(name)
                                .nationality(country)
                                .build();
                        toSave = driverService.save(toSave);
                        drivers.add(toSave);
                    }
                }
        );
    }

    private List<Location> extractOrCreateLocations(List<List<String>> records) {

        List<List<String>> locations = new ArrayList<>(records.subList(0, 3));
        for (int i = 0; i < locations.size(); i++) {
            locations.set(i, locations.get(i).subList(LOCATIONS_STARTS_AT, locations.get(i).size()));
        }

        List<Location> locationsInSeason = new ArrayList<>();
        //loop through locations tag and find in database or create
        locations.get(0).forEach(
                tag -> {
                    if (tag.length() == 3) {
                        Optional<Location> location = locationService.findByLocationTag(tag);
                        if (location.isPresent()) {
                            locationsInSeason.add(location.get());
                        } else {
                            //create location
                            int index = locations.get(0).indexOf(tag);
                            Location savedLocation = locationService.save(new Location(
                                    null,
                                    locations.get(2).get(index),
                                    locations.get(0).get(index),
                                    locations.get(1).get(index)));
                            locationsInSeason.add(savedLocation);
                        }
                    }
                }
        );
        return locationsInSeason;
    }


    private void readDataFromFile(List<List<String>> records) {
        try {
            File file = ResourceUtils.getFile("classpath:data/" + fileName);   //creating a new file instance
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(";");
                    records.add(Arrays.asList(values));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}