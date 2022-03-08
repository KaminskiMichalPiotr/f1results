package com.f1.f1results.objects.team;

import com.f1.f1results.objects.driver.Driver;
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
        List<Long> ids = teams.stream()
                .map(Team::getId)
                .collect(Collectors.toList());
        List<Team> teamList = teamRepository.findAllById(ids);
        teamList.forEach(team -> team.addDriver(driver));
        return teamRepository.saveAll(teamList);
    }

    public Optional<Team> getById(Long id) {
        return teamRepository.findById(id);
    }
}
