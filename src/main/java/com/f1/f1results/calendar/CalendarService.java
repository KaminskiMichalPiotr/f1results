package com.f1.f1results.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarService {

    CalendarRepository calendarRepository;

    @Autowired
    public CalendarService(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    public void saveCalendar(Calendar calendar) {
        calendarRepository.save(calendar);
    }

    public List<Calendar> getAllCalendars() {
        return calendarRepository.findAll();
    }

}
