package com.f1.f1results.entities.driver;

import com.f1.f1results.entities.driverresult.DriverResult;
import com.f1.f1results.entities.driverresult.DriverResultService;
import com.f1.f1results.entities.team.TeamService;
import com.f1.f1results.exceptions.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    private final DriverRepository driverRepository;
    private final DriverResultService driverResultService;
    private final TeamService teamService;

    @Autowired
    public DriverService(DriverRepository driverRepository,
                         @Lazy DriverResultService driverResultService,
                         @Lazy TeamService teamService) {
        this.driverRepository = driverRepository;
        this.driverResultService = driverResultService;
        this.teamService = teamService;
    }

    public List<Driver> saveDrivers(List<Driver> drivers) {
        return driverRepository.saveAll(drivers);
    }

    public Driver save(Driver driver) {
        return driverRepository.save(driver);
    }

    public List<Driver> getDrivers() {
        return driverRepository.findAll();
    }

    public Optional<Driver> getById(Long id) {
        return driverRepository.findById(id);
    }

    public Optional<Driver> findByName(String name) {
        return driverRepository.findByName(name);
    }

    @Transactional
    public void deleteDriverById(Long id) throws IdNotFoundException {
        Driver driver = driverRepository.findById(id).orElseThrow(IdNotFoundException::new);
        //delete all driver results
        List<DriverResult> driverResults = driverResultService.getDriverResultByDriver(driver);
        driverResultService.deleteDriverResults(driverResults);
        //remove driver from all teams
        teamService.removeDriverFromTeams(driver, driver.getTeams());
        driverRepository.delete(driver);
    }

}
