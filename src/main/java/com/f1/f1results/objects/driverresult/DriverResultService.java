package com.f1.f1results.objects.driverresult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverResultService {

    DriverResultRepository driverResultRepository;

    @Autowired
    public DriverResultService(DriverResultRepository driverResultRepository) {
        this.driverResultRepository = driverResultRepository;
    }

    public DriverResult save(DriverResult driverResult) {
        return driverResultRepository.save(driverResult);
    }
}
