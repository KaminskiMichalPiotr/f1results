package com.f1.f1results;

import com.f1.f1results.calendar.Calendar;
import com.f1.f1results.calendar.CalendarService;
import com.f1.f1results.location.Location;
import com.f1.f1results.location.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "test")
public class TestController {

    CalendarService calendarService;
    LocationService locationService;

    @Autowired
    public TestController(CalendarService calendarService, LocationService locationService) {
        this.calendarService = calendarService;
        this.locationService = locationService;
    }

    Location location;
    Location location2;

    @GetMapping
    public List<Calendar> getTest() {
        location = new Location(null, "London", "ENG", "England", null);
        location2 = new Location(null, "Warsaw", "POL", "Poland", null);
        location = locationService.saveLocation(location);
        location2 = locationService.saveLocation(location2);
        Calendar calendar = new Calendar(null, List.of(location, location2));
        Calendar calendar1 = new Calendar(null, List.of(location, location2));
        calendarService.saveCalendar(calendar);
        calendarService.saveCalendar(calendar1);
        return calendarService.getAllCalendars();
    }

    @GetMapping(path = "/1")
    public Location getTesting() {
        location = locationService.findByLocationTag(location.getLocationTag()).isPresent() ?
                locationService.findByLocationTag(location.getLocationTag()).get() : null;
        return location;
    }

}
