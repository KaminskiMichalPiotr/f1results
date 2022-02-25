package com.f1.f1results;

import com.f1.f1results.objects.calendar.Calendar;
import com.f1.f1results.objects.calendar.CalendarService;
import com.f1.f1results.objects.driver.Driver;
import com.f1.f1results.objects.driver.DriverService;
import com.f1.f1results.objects.driverresult.DriverResult;
import com.f1.f1results.objects.driverresult.DriverResultService;
import com.f1.f1results.objects.location.Location;
import com.f1.f1results.objects.location.LocationService;
import com.f1.f1results.objects.raceevent.RaceEvent;
import com.f1.f1results.objects.raceevent.RaceEventService;
import com.f1.f1results.objects.raceresult.RaceResult;
import com.f1.f1results.objects.raceresult.RaceResultService;
import com.f1.f1results.objects.season.Season;
import com.f1.f1results.objects.season.SeasonService;
import com.f1.f1results.objects.team.Team;
import com.f1.f1results.objects.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.f1.f1results.objects.driverresult.PointsScoringSystem.getPointsScoredByPosition;

@SpringBootApplication
public class F1resultApplication {

	public static void main(String[] args) {
		SpringApplication.run(F1resultApplication.class, args);
	}


	CalendarService calendarService;
	LocationService locationService;
	DriverService driverService;
	TeamService teamService;
	RaceResultService raceResultService;
	DriverResultService driverResultService;
	RaceEventService raceEventService;
	SeasonService seasonService;

	@Autowired
	public F1resultApplication(CalendarService calendarService, LocationService locationService,
							   DriverService driverService, TeamService teamService,
							   RaceResultService raceResultService, DriverResultService driverResultService,
							   RaceEventService raceEventService, SeasonService seasonService) {
		this.calendarService = calendarService;
		this.locationService = locationService;
		this.driverService = driverService;
		this.teamService = teamService;
		this.raceResultService = raceResultService;
		this.driverResultService = driverResultService;
		this.raceEventService = raceEventService;
		this.seasonService = seasonService;
	}


	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {
			Calendar calendar = new Calendar();
			Location location = new Location(null, "London", "ENG", "England", new HashSet<>());
			Location location2 = new Location(null, "Warsaw", "POL", "Poland", new HashSet<>());
			Driver driver = new Driver(null, "Max Verstappen", "05-05-1985", "Dutch", null);
			Driver driver2 = new Driver(null, "Charles Leclerc", "06-07-2002", "Monegasque", null);
			Team team = new Team(null, "Alpha Tauri", "ALF", Set.of(driver, driver2), "Italy");

			//location = locationService.save(location);
			//location2 = locationService.save(location2);

			calendar.addLocation(location);
			calendar.addLocation(location2);
			//calendar.setLocations(List.of(location, location2));
			calendar = calendarService.save(calendar);

			driver = driverService.save(driver);
			driver2 = driverService.save(driver2);

			team = teamService.save(team);

			DriverResult driverResult = new DriverResult(null, driver, 1, getPointsScoredByPosition(1, false), team);
			DriverResult driverResult2 = new DriverResult(null, driver2, 2, getPointsScoredByPosition(2, false), team);

			driverResult = driverResultService.save(driverResult);
			driverResult2 = driverResultService.save(driverResult2);


			RaceResult raceResult = new RaceResult(null, List.of(driverResult, driverResult2));
			raceResult = raceResultService.save(raceResult);


			Season season = new Season(null, null, calendar, 2020);
			season = seasonService.save(season);

			RaceEvent raceEvent = new RaceEvent(null, location, raceResult, season, "25-05-2021", 1);
			raceEvent = raceEventService.save(raceEvent);


		};
	}


}
