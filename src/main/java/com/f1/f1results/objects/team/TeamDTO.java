package com.f1.f1results.objects.team;

import com.f1.f1results.objects.driver.Driver;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeamDTO {

    public static TeamDTO from(Team team) {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setId(team.getId());
        teamDTO.setTeamName(team.getTeamName());
        teamDTO.setTeamTag(team.getTeamTag());
        teamDTO.setDriversIDs(team.getDrivers().stream().map(Driver::getId).collect(Collectors.toSet()));
        teamDTO.setCountry(team.getCountry());
        return teamDTO;
    }


    private Long id;
    private String teamName;
    private String teamTag;
    private Set<Long> driversIDs;
    private String country;

}
