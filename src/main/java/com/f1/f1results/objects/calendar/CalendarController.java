package com.f1.f1results.objects.calendar;

import com.f1.f1results.dtos.CalendarDisplayDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "calendar")
public class CalendarController {

    CalendarService calendarService;

    @Autowired
    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping(path = "/{year}")
    public ResponseEntity<CalendarDisplayDTO> getCalendarForSelectedSeason(@PathVariable(name = "year") int year) {
        Optional<CalendarDisplayDTO> calendarDisplayDTO = calendarService.getCalendarForSelectedSeason(year);
        if (calendarDisplayDTO.isPresent()) {
            return ResponseEntity.ok(calendarDisplayDTO.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
