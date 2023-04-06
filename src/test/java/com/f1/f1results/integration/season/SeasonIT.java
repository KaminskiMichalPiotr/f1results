package com.f1.f1results.integration.season;

import com.f1.f1results.entities.season.Season;
import com.f1.f1results.entities.season.SeasonRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.f1.f1results.integration.RetrieveJwtTokenAndHeaders.*;
import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(
        locations = "classpath:application-it.properties"
)
@AutoConfigureMockMvc
public class SeasonIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @AfterEach
    public void cleanUp() {
        seasonRepository.deleteAll();
    }

    private final int SEASON_YEAR = 2018;

    @Autowired
    private SeasonRepository seasonRepository;

    @Test
    void shouldSaveSeason() throws Exception {
        given()
                .header(authorizationHeader())
                .header(CONTENT_TYPE_JSON_HEADER)
                .body(toJSON(new Season(SEASON_YEAR)))
                .when()
                .post("/season")
                .then()
                .assertThat()
                .statusCode(200);

        Season[] seasons = given()
                .when()
                .get("/season")
                .as(Season[].class);

        boolean present = Arrays.stream(seasons).anyMatch(season -> season.getSeasonYear() == SEASON_YEAR);

        Assertions.assertTrue(present);
    }

    @Test
    void shouldGetListOfSeasons() {
        List<Integer> years = Arrays.asList(2020, 2021);
        years.forEach(year -> seasonRepository.save(new Season(year)));

        Season[] seasons = given()
                .when()
                .get("/season")
                .as(Season[].class);

        Assertions.assertTrue(Arrays.stream(seasons)
                .map(Season::getSeasonYear).collect(Collectors.toList()).containsAll(years));
    }

    @Test
    void shouldReturnListOfSeasonsYear() {
        List<Integer> years = Arrays.asList(2020, 2021);
        years.forEach(year -> seasonRepository.save(new Season(year)));

        Integer[] seasonsYears = given()
                .when()
                .get("/season/seasonsYears").then()
                .statusCode(200)
                .extract()
                .as(Integer[].class);

        Assertions.assertTrue(Arrays.asList(seasonsYears).containsAll(years));
    }


}
