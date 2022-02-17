package com.f1.f1results.objects.calendar;

import com.f1.f1results.dtos.CalendarDisplayDTO;
import com.f1.f1results.dtos.LocationDTO;
import com.f1.f1results.objects.location.Location;
import com.f1.f1results.objects.season.Season;
import com.f1.f1results.objects.season.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CalendarService {

    CalendarRepository calendarRepository;
    SeasonService seasonService;

    @Autowired
    public CalendarService(CalendarRepository calendarRepository, SeasonService seasonService) {
        this.calendarRepository = calendarRepository;
        this.seasonService = seasonService;
    }

    public Calendar save(Calendar calendar) {
        return calendarRepository.save(calendar);
    }

    public List<Calendar> getAllCalendars() {
        return calendarRepository.findAll();
    }


    public Optional<CalendarDisplayDTO> getCalendarForSelectedSeason(int year) {
        Optional<Season> season = seasonService.findSeasonByYear(year);
        if (season.isPresent()) {
            return Optional.of(convertCalendarToDto(season.get()));
        } else {
            return Optional.empty();
        }
    }

    private CalendarDisplayDTO convertCalendarToDto(Season season) {
        Calendar calendar = season.getCalendar();
        List<Location> locations = calendar.getLocations();
        List<LocationDTO> locationDTOS = locations.stream().map(location -> new LocationDTO(location.getLocation(),
                location.getLocationTag(), location.getCountry())).collect(Collectors.toList());
        return new CalendarDisplayDTO(season.getSeasonYear(), locationDTOS);
    }

}
