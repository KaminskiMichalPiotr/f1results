package com.f1.f1results.objects.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    LocationRepository locationRepository;

    public Location save(Location location) {
        return locationRepository.save(location);
    }

    public List<Location> saveLocations(List<Location> locations) {
        return locationRepository.saveAll(locations);
    }


    public Optional<Location> findById(Long id) {
        return locationRepository.findById(id);
    }

    public Optional<Location> findByLocationTag(String tag) {
        return locationRepository.findByLocationTag(tag);
    }

}
