package com.f1.f1results.integration.location;

import com.f1.f1results.entities.location.Location;
import com.f1.f1results.entities.location.LocationRepository;
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
import java.util.Objects;

import static com.f1.f1results.integration.RetrieveJwtTokenAndHeaders.*;
import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(
        locations = "classpath:application-it.properties"
)
@AutoConfigureMockMvc
public class LocationIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @AfterEach
    public void cleanUp() {
        locationRepository.deleteAll();
    }

    @Autowired
    private LocationRepository locationRepository;

    @Test
    void shouldSaveLocation() throws Exception {
        given()
                .header(authorizationHeader())
                .header(CONTENT_TYPE_JSON_HEADER)
                .body(toJSON(testLocation()))
                .when()
                .post("/location")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    void shouldGetLocation() throws Exception {
        given()
                .header(authorizationHeader())
                .header(CONTENT_TYPE_JSON_HEADER)
                .body(toJSON(testLocation()))
                .when()
                .post("/location")
                .then()
                .assertThat()
                .statusCode(200);

        Location[] locations = given()
                .when()
                .get("/location")
                .as(Location[].class);

        boolean present = Arrays.stream(locations).anyMatch(location -> Objects.equals(location.getLocationTag(), testLocation().getLocationTag()));

        Assertions.assertTrue(present);

    }


    private Location testLocation() {
        return new Location("Test", "TST", "TestCountry");
    }
}
