package com.f1.f1results.entities.season;

import com.f1.f1results.entities.driver.Driver;
import com.f1.f1results.entities.driverresult.DriverResult;
import com.f1.f1results.entities.driverresult.RaceDistance;
import com.f1.f1results.entities.raceevent.RaceEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

import static com.f1.f1results.entities.driverresult.PointsScoringSystem.getPointsScoredByPosition;

@Getter
@Setter
@NoArgsConstructor
public class SeasonResultDto {

    private List<String> locationsTags;
    private List<DriverResultDto> results;

    public SeasonResultDto(List<String> locationsTags, List<DriverResultDto> results) {
        this.locationsTags = locationsTags;
        this.results = results;
    }

    public static SeasonResultDto fromSeason(Season season) {

        List<String> locationsTags = extractLocations(season);

        List<DriverResultDto> results = new ArrayList<>();


        List<RaceEvent> sortedRaces = season.getRaceEvents().stream()
                .sorted(Comparator.comparingInt(RaceEvent::getIndex)).collect(Collectors.toList());

        sortedRaces.forEach(raceEvent ->
                raceEvent.getDriverResults().forEach(
                        driverResult -> {
                            Optional<DriverResultDto> inList = results.stream()
                                    .filter(result -> result.driver == driverResult.getDriver())
                                    .findFirst();
                            //driver exist, add result
                            if (inList.isPresent()) {
                                addResult(raceEvent, driverResult, inList.get());
                            }
                            //add driver and result
                            else {
                                addNewDriverAndResult(results, raceEvent, driverResult);
                            }
                        }
                )
        );

        calculatePoints(results);

        List<DriverResultDto> sorted = results.stream()
                .sorted(Comparator.comparingDouble(DriverResultDto::getTotalPoints).reversed())
                .collect(Collectors.toList());

        calculatePosition(sorted);

        return new SeasonResultDto(locationsTags, sorted);
    }

    private static List<String> extractLocations(Season season) {
        return season.getRaceEvents().stream()
                .sorted(Comparator.comparingInt(RaceEvent::getIndex))
                .map(raceEvent -> raceEvent.getLocation().getLocationTag())
                .collect(Collectors.toList());
    }

    private static void calculatePosition(List<DriverResultDto> results) {
        for (int i = 1; i <= results.size(); i++) {
            results.get(i - 1).position = i;
        }
    }

    private static void calculatePoints(List<DriverResultDto> results) {
        results.forEach(result -> result.totalPoints = result.getPositionInRace().values().stream()
                .map(position -> getPointsScoredByPosition(position, RaceDistance.FULL))
                .mapToDouble(i -> i)
                .sum());
    }

    private static void addResult(RaceEvent raceEvent, DriverResult driverResult, DriverResultDto inList) {
        inList.getPositionInRace().put(
                raceEvent.getLocation().getLocationTag(),
                driverResult.getPosition());
    }

    private static void addNewDriverAndResult(List<DriverResultDto> results, RaceEvent raceEvent, DriverResult driverResult) {
        DriverResultDto resultDto = new DriverResultDto();
        resultDto.setDriver(driverResult.getDriver());
        resultDto.getPositionInRace().put(
                raceEvent.getLocation().getLocationTag(),
                driverResult.getPosition());
        results.add(resultDto);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    private static class DriverResultDto {
        private int position;
        private double totalPoints;
        private Driver driver;
        private Map<String, Integer> positionInRace = new HashMap<>();
    }


}
