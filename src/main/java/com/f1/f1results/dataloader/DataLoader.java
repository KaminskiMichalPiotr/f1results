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
import org.springframework.beans.factory.annotation.Value;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.f1.f1results.entities.driverresult.PointsScoringSystem.getPointsScoredByPosition;

@Component
public class DataLoader implements CommandLineRunner {

    private static final int RESULT_STARTS_AT_Y = 4;

    private static final int LOCATIONS_STARTS_AT = 6;
    private static final int DRIVER_STARTS_AT = 1;
    private static final int TEAM_STARTS_AT = 3;
    private static final int RESULT_STARTS_AT_X = 6;
    private final LocationService locationService;
    private final DriverService driverService;
    private final TeamService teamService;
    private final SeasonService seasonService;
    private final RaceEventService raceEventService;
    private final DriverResultService driverResultService;
    private final ResourcePatternResolver resourcePatternResolver;
    @Value("${dataloader.shouldLoadData}")
    private boolean shouldLoadData;

    @Autowired
    public DataLoader(LocationService locationService, DriverService driverService,
                      TeamService teamService, SeasonService seasonService,
                      RaceEventService raceEventService, DriverResultService driverResultService,
                      ResourcePatternResolver resourcePatternResolver) {
        this.locationService = locationService;
        this.driverService = driverService;
        this.teamService = teamService;
        this.seasonService = seasonService;
        this.raceEventService = raceEventService;
        this.driverResultService = driverResultService;
        this.resourcePatternResolver = resourcePatternResolver;
    }

    @Override
    public void run(String... args) throws Exception {
        if (shouldLoadData) {
            Resource[] resources = resourcePatternResolver.getResources("classpath:data/*.csv");
            for (Resource r : resources) {
                readData(r.getFile().getAbsolutePath());
            }
        }
    }

    public void readData(String path) {

        List<List<String>> records = readDataFromFile(path);
        List<Location> locations = extractOrCreateLocations(records);
        List<Driver> drivers = new ArrayList<>();
        List<Team> teams = new ArrayList<>();
        extractOrCreateDriversAndTeams(drivers, teams, records);


        Season season = createSeason(Integer.parseInt(path.substring(path.length() - 8, path.length() - 4)));
        List<RaceEvent> raceEvents = createRaceEvents(locations, season, records);
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
                boolean hasFastestLap = hasFastestLap(row.get(j));
                int position = racePosition(row.get(j));
                int positionInSprint = sprintRacePosition(row.get(j));
                DriverResult save;
                if (position >= 1) {
                    DriverResult driverResult = new DriverResult(
                            position,
                            hasFastestLap,
                            drivers.get(i),
                            positionInSprint,
                            0,
                            teams.get(i)
                    );
                    driverResult.setPoints(getPointsScoredByPosition(driverResult, raceEvents.get(j).getRaceDistance()));
                    save = driverResultService.save(driverResult);
                    raceEvents.get(j).addDriverResult(save);
                    raceEventService.save(raceEvents.get(j));
                } else if (position == -1) {
                    //TODO simplyfy
                    DriverResult driverResult = new DriverResult(
                            404,
                            hasFastestLap,
                            drivers.get(i),
                            positionInSprint,
                            0,
                            teams.get(i)
                    );
                    driverResult.setPoints(getPointsScoredByPosition(driverResult, raceEvents.get(j).getRaceDistance()));
                    save = driverResultService.save(driverResult);
                    raceEvents.get(j).addDriverResult(save);
                    raceEventService.save(raceEvents.get(j));
                }

            }

        }
    }

    private boolean hasFastestLap(String s) {
        Pattern p = Pattern.compile("F");
        Matcher m = p.matcher(s);
        return m.find();
    }

    private int sprintRacePosition(String s) {
        Pattern p = Pattern.compile("s[1-3]");
        Matcher m = p.matcher(s);
        if (m.find())
            return Integer.parseInt(m.group().substring(1));
        return 0;
    }

    private int racePosition(String s) {
        if (s.isBlank())
            return -2;
        Pattern p = Pattern.compile("[1-9]?[0-9]");
        Matcher m = p.matcher(s);
        if (m.find() && s.charAt(0) != 's')
            return Integer.parseInt(m.group());
        return -1;
    }

    private List<RaceEvent> createRaceEvents(List<Location> locations, Season season, List<List<String>> records) {
        List<RaceDistance> raceDistances = extractRaceDistances(records);
        List<RaceEvent> raceEvents = new ArrayList<>();
        for (int i = 0; i < locations.size(); i++) {
            //TODO: Add race date to excel and read
            RaceEvent raceEvent = new RaceEvent(
                    locations.get(i),
                    season,
                    "20-02-2020",
                    i + 1,
                    raceDistances.get(i)
            );
            raceEvents.add(raceEventService.save(raceEvent));
        }
        return raceEvents;
    }

    private List<RaceDistance> extractRaceDistances(List<List<String>> records) {
        List<String> raceDistances = new ArrayList<>(
                records.get(RESULT_STARTS_AT_Y - 1).subList(LOCATIONS_STARTS_AT,
                        records.get(RESULT_STARTS_AT_Y - 1).size()));
        List<RaceDistance> result = new ArrayList<>(raceDistances.size());
        for (String r : raceDistances) {
            switch (r) {
                case "0,5":
                    result.add(RaceDistance.HALF);
                    break;
                case "0,25":
                    result.add(RaceDistance.QUARTER);
                    break;
                default:
                    result.add(RaceDistance.FULL);
            }
        }
        return result;
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
        List<List<String>> teamRecords = new ArrayList<>(records.subList(RESULT_STARTS_AT_Y, records.size()));
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
        List<List<String>> driversRecords = new ArrayList<>(records.subList(RESULT_STARTS_AT_Y, records.size()));
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


    private List<List<String>> readDataFromFile(String path) {
        List<List<String>> records = new ArrayList<>();
        try {
            File file = ResourceUtils.getFile(path);   //creating a new file instance
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(";");
                    records.add(Arrays.asList(values));
                }
            }
            return records;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}