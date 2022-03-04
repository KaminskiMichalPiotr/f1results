package com.f1.f1results.objects.driverresult;

import com.f1.f1results.objects.raceevent.RaceEvent;
import com.f1.f1results.objects.raceevent.RaceEventService;
import com.f1.f1results.objects.raceresult.RaceResult;
import com.f1.f1results.objects.raceresult.RaceResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class DriverResultService {

    DriverResultRepository driverResultRepository;
    RaceEventService raceEventService;
    RaceResultService raceResultService;

    @Autowired
    public DriverResultService(DriverResultRepository driverResultRepository, RaceEventService raceEventService, RaceResultService raceResultService) {
        this.driverResultRepository = driverResultRepository;
        this.raceEventService = raceEventService;
        this.raceResultService = raceResultService;
    }

    public DriverResult save(DriverResult driverResult) {
        return driverResultRepository.save(driverResult);
    }

    public List<DriverResult> getDriverResultsByRace(Long raceId) {
        Optional<RaceEvent> raceEvent = this.raceEventService.getById(raceId);
        if (raceEvent.isPresent()) {
            RaceResult raceResult = raceEvent.get().getRaceResult();
            if (raceResult != null)
                return raceResult.getDriverResults();
        }
        return Collections.emptyList();
    }
}
