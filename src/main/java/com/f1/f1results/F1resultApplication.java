package com.f1.f1results;

import com.f1.f1results.calendar.Calendar;
import com.f1.f1results.calendar.CalendarService;
import com.f1.f1results.driver.Driver;
import com.f1.f1results.driver.DriverService;
import com.f1.f1results.driverresult.DriverResult;
import com.f1.f1results.driverresult.DriverResultService;
import com.f1.f1results.location.Location;
import com.f1.f1results.location.LocationService;
import com.f1.f1results.raceevent.RaceEvent;
import com.f1.f1results.raceevent.RaceEventService;
import com.f1.f1results.raceresult.RaceResult;
import com.f1.f1results.raceresult.RaceResultService;
import com.f1.f1results.season.Season;
import com.f1.f1results.season.SeasonService;
import com.f1.f1results.team.Team;
import com.f1.f1results.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

import static com.f1.f1results.raceresult.PointsScoringSystem.getPointsScoredByPosition;

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
			Location location = new Location(null, "London", "ENG", "England", null);
			Location location2 = new Location(null, "Warsaw", "POL", "Poland", null);
			Driver driver = new Driver(null, "Max Verstappen", "05-05-1985", "Dutch");
			Driver driver2 = new Driver(null, "Charles Leclerc", "06-07-2002", "Monegasque");
			Team team = new Team(null, "Alpha Tauri", "ALF", Set.of(driver, driver2), "Italy");

			location = locationService.save(location);
			location2 = locationService.save(location2);

			calendar.setLocations(List.of(location, location2));
			calendarService.save(calendar);

			driver = driverService.save(driver);
			driver2 = driverService.save(driver2);

			team = teamService.save(team);

			DriverResult driverResult = new DriverResult(null, driver, 1, getPointsScoredByPosition(1, false), team);
			DriverResult driverResult2 = new DriverResult(null, driver2, 2, getPointsScoredByPosition(2, false), team);

			driverResult = driverResultService.save(driverResult);
			driverResult2 = driverResultService.save(driverResult2);


			RaceResult raceResult = new RaceResult(null, List.of(driverResult, driverResult2));
			raceResult = raceResultService.save(raceResult);

			RaceEvent raceEvent = new RaceEvent(null, location, raceResult);
			raceEvent = raceEventService.save(raceEvent);

			Season season = new Season(null, List.of(raceEvent), calendar);
			season = seasonService.save(season);


		};
	}


}
