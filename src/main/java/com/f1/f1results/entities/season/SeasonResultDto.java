package com.f1.f1results.entities.season;

import com.f1.f1results.entities.driver.Driver;
import com.f1.f1results.entities.driverresult.DriverResult;
import com.f1.f1results.entities.raceevent.RaceEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor
@Setter
@Getter
public class SeasonResultDto {

    private List<String> locationsTags;

    private List<DriverResultDto> results;

    public SeasonResultDto(List<String> locationsTags, List<DriverResultDto> results) {
        this.locationsTags = locationsTags;
        this.results = results;
    }

    public static SeasonResultDto fromSeason(Season season) {

        //List of locations tags in season calendar
        List<String> locationsTags = extractLocations(season);
        //List containing each driver result
        List<DriverResultDto> results = new ArrayList<>();

        extractDriverResultsFromSeason(season, results);

        List<DriverResultDto> sorted = results.stream()
                .sorted(Comparator.comparingDouble(DriverResultDto::getTotalPoints).reversed())
                .collect(Collectors.toList());

        calculatePosition(sorted);

        return new SeasonResultDto(locationsTags, sorted);
    }

    private static void extractDriverResultsFromSeason(Season season, List<DriverResultDto> results) {
        List<RaceEvent> sortedRaces = season.getRaceEvents().stream()
                .sorted(Comparator.comparingInt(RaceEvent::getIndex))
                .collect(Collectors.toList());

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
    }

    private static void calculatePosition(List<DriverResultDto> results) {
        //TODO if two drivers have same amount of points higher place is taken by driver with highest position
        for (int i = 1; i <= results.size(); i++) {
            results.get(i - 1).position = i;
        }
    }

    private static List<String> extractLocations(Season season) {
        return season.getRaceEvents().stream()
                .sorted(Comparator.comparingInt(RaceEvent::getIndex))
                .map(raceEvent -> raceEvent.getLocation().getLocationTag())
                .collect(Collectors.toList());
    }

    private static void addResult(RaceEvent raceEvent, DriverResult driverResult, DriverResultDto inList) {
        inList.getPositionInRace().put(
                raceEvent.getLocation().getLocationTag(),
                buildStringResult(driverResult.getPosition(),
                        driverResult.hasFastestLap(),
                        driverResult.getSprintRacePosition()));
        inList.totalPoints += driverResult.getPoints();
    }

    private static void addNewDriverAndResult(List<DriverResultDto> results, RaceEvent raceEvent, DriverResult driverResult) {
        DriverResultDto resultDto = new DriverResultDto();
        resultDto.setDriver(driverResult.getDriver());
        resultDto.getPositionInRace().put(
                raceEvent.getLocation().getLocationTag(),
                buildStringResult(driverResult.getPosition(),
                        driverResult.hasFastestLap(),
                        driverResult.getSprintRacePosition()));
        resultDto.setTotalPoints(driverResult.getPoints());
        results.add(resultDto);

    }

    private static String buildStringResult(int pos, boolean hasFastestLap, int posInSprint) {
        String result = pos + "<";
        if (1 <= posInSprint && posInSprint <= 3)
            result += String.valueOf(posInSprint);
        if (hasFastestLap)
            result += "F";
        result += ">";

        return result;
    }

    //Each driver results
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    private static class DriverResultDto {
        private int position;
        private double totalPoints = 0;
        private Driver driver;
        private Map<String, String> positionInRace = new HashMap<>();
    }


}
