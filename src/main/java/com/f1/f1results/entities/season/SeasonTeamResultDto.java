package com.f1.f1results.entities.season;

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
        List<TeamResultDto> results = new ArrayList<>();


        return new SeasonTeamResultDto();
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
    private class TeamResultDto {
        private int position;
        private double totalPoints;
        private Team team;
        private Map<String, Positions> positionInRace = new HashMap<>();
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    private class Positions {
        private int driverOnePosition;
        private int driverTwoPosition;
    }


}
