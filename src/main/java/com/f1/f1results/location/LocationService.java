package com.f1.f1results.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    LocationRepository locationRepository;

    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    public Optional<Location> findById(Long id) {
        return locationRepository.findById(id);
    }

    public Optional<Location> findByLocationTag(String tag) {
        return locationRepository.findByLocationTag(tag);
    }

}
