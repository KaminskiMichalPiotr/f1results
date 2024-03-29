package com.f1.f1results.entities.team;

import com.f1.f1results.entities.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TeamService {

    TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team save(Team team) {
        return teamRepository.save(team);
    }

    public List<Team> getTeams() {
        return teamRepository.findAll();
    }

    public List<Team> addDriverToTeams(Driver driver, Set<Team> teams) {
        List<Team> teamList = getTeams(teams);
        teamList.forEach(team -> team.addDriver(driver));
        return teamRepository.saveAll(teamList);
    }

    public Optional<Team> getById(Long id) {
        return teamRepository.findById(id);
    }

    public Optional<Team> findByTag(String teamTag) {
        return teamRepository.findByTeamTag(teamTag);
    }

    public List<Team> saveAll(List<Team> teams) {
        return teamRepository.saveAll(teams);
    }

    public List<Team> removeDriverFromTeams(Driver driver, Set<Team> teams) {
        List<Team> teamList = getTeams(teams);
        teamList.forEach(team -> team.removeDriver(driver));
        return teamRepository.saveAll(teamList);
    }

    private List<Team> getTeams(Set<Team> teams) {
        List<Long> ids = teams.stream()
                .map(Team::getId)
                .collect(Collectors.toList());
        List<Team> teamList = teamRepository.findAllById(ids);
        return teamList;
    }
}
