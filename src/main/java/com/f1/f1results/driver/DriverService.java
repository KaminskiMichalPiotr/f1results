package com.f1.f1results.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

    DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
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
}
