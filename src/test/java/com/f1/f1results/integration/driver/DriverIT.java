package com.f1.f1results.integration.driver;

import com.f1.f1results.entities.driver.Driver;
import com.f1.f1results.entities.driver.DriverRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static com.f1.f1results.integration.RetrieveJwtTokenAndHeaders.*;
import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(
        locations = "classpath:application-it.properties"
)
@AutoConfigureMockMvc
public class DriverIT {

    @LocalServerPort
    private int port;

    @Autowired
    DriverRepository driverRepository;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @AfterEach
    public void cleanUp() {
        // driverRepository.deleteAll();
    }

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldSaveDriver() throws Exception {
        given()
                .header(authorizationHeader())
                .header(CONTENT_TYPE_JSON_HEADER)
                .body(toJSON(testDriver()))
                .when()
                .post("/driver")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    void shouldDeleteDriver() throws Exception {
        Driver driver = given()
                .header(authorizationHeader())
                .header(CONTENT_TYPE_JSON_HEADER)
                .body(toJSON(testDriver()))
                .when()
                .post("/driver")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(Driver.class);

        given()
                .header(authorizationHeader())
                .when()
                .delete("/driver/" + driver.getId())
                .then()
                .assertThat()
                .statusCode(HttpStatus.ACCEPTED.value());
    }

    Driver testDriver() {
        return new Driver("Test", "01-01-2020", "Tester");
    }
}
