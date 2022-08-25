package com.f1.f1results.integration.season;

import com.f1.f1results.entities.season.Season;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;

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

    private final int SEASON_YEAR = 2018;

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


}
