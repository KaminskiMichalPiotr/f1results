package com.f1.f1results.entities.season;

import com.f1.f1results.entities.driverresult.DriverResult;
import com.f1.f1results.entities.raceevent.RaceEvent;
import com.f1.f1results.entities.team.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeasonTeamResultDto {

    private List<String> locationsTags;
    private List<TeamResultDto> results;


    public static SeasonTeamResultDto fromSeason(Season season) {

        List<String> locationsTags = extractLocations(season);
        List<TeamResultDto> results = extractResults(season);
        results = calculatePosition(results);

        return new SeasonTeamResultDto(locationsTags, results);
    }

    private static List<TeamResultDto> calculatePosition(List<TeamResultDto> results) {
        List<TeamResultDto> sorted = results.stream().sorted(Comparator.comparingDouble(TeamResultDto::getTotalPoints).reversed())
                .collect(Collectors.toList());
        for (int i = 0; i < sorted.size(); i++) {
            sorted.get(i).setPosition(i + 1);
        }
        return sorted;
    }

    private static List<TeamResultDto> extractResults(Season season) {
        List<TeamResultDto> teamResultDtos = new ArrayList<>();

        List<RaceEvent> raceEvents = season.getRaceEvents().stream()
                .sorted(Comparator.comparingInt(RaceEvent::getIndex))
                .collect(Collectors.toList());

        //loop through races
        raceEvents.forEach(raceEvent -> {
            //loop through race events
            List<DriverResult> driverResults = raceEvent.getDriverResults();
            driverResults.forEach(driverResult ->
                    addDriverResultToTeamResultList(
                            driverResult,
                            teamResultDtos,
                            raceEvent)
            );
        });

        return teamResultDtos;
    }

    private static void addDriverResultToTeamResultList(DriverResult driverResult, List<TeamResultDto> teamResultDtos, RaceEvent raceEvent) {
        Team team = driverResult.getTeam();

        Optional<TeamResultDto> optionalTeamResultDto = teamResultDtos.stream()
                .filter(teamResultDto -> Objects.equals(teamResultDto.getTeam().getId(), team.getId())).findFirst();
        TeamResultDto teamResultDto;

        //add team to list if not present or get value
        if (optionalTeamResultDto.isEmpty()) {
            teamResultDto = new TeamResultDto();
            teamResultDto.setTeam(team);
            teamResultDtos.add(teamResultDto);
        } else teamResultDto = optionalTeamResultDto.get();

        //check if race is present in teamResultDto or add race
        Positions posInRace = teamResultDto.getPositionInRace().get(raceEvent.getLocation().getLocationTag());
        if (posInRace == null) {
            Positions pos = new Positions();
            pos.setDriverOnePosition(driverResult.getPosition());
            teamResultDto.getPositionInRace().put(raceEvent.getLocation().getLocationTag(), pos);
        } else {
            posInRace.setDriverTwoPosition(driverResult.getPosition());
        }
        teamResultDto.increasePoints(driverResult.getPoints());
    }


    private static List<String> extractLocations(Season season) {
        return season.getRaceEvents().stream()
                .sorted(Comparator.comparingInt(RaceEvent::getIndex))
                .map(raceEvent -> raceEvent.getLocation().getLocationTag())
                .collect(Collectors.toList());
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    private static class TeamResultDto {
        private int position;
        private double totalPoints;
        private Team team;
        private Map<String, Positions> positionInRace = new HashMap<>();

        public void increasePoints(double points) {
            totalPoints += points;
        }

    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    private static class Positions {
        private int driverOnePosition;
        private int driverTwoPosition;
    }


}
