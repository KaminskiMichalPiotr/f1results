package com.f1.f1results.integration;

import com.f1.f1results.objects.team.Team;
import com.f1.f1results.objects.team.TeamRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static com.f1.f1results.integration.RetrieveJwtToken.retrieveJwtToken;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-it.properties"
)
@AutoConfigureMockMvc
public class TeamIT {

    private String token;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TeamRepository teamRepository;


    @BeforeEach
    void setUp() throws Exception {
        Team team = new Team(
                null,
                "Team",
                "TAG",
                Collections.emptySet(),
                "Country");
        teamRepository.save(team);
        token = retrieveJwtToken(mockMvc, objectMapper);
    }

    @AfterEach
    void tearDown() {
        teamRepository.deleteAll();
    }

    @Test
    void shouldCreateTeam() throws Exception {
        Team team = getTestTeam();
        mockMvc.perform(
                        post("/team")
                                .header(HttpHeaders.AUTHORIZATION, token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(team)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldNotCreateTeamWithoutUniqueTagOrName() throws Exception {
        Team team = getTestTeam();

        mockMvc.perform(
                post("/team")
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(team)));

        mockMvc.perform(
                        post("/team")
                                .header(HttpHeaders.AUTHORIZATION, token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(team)))
                .andExpect(status().isUnprocessableEntity());

    }


    @Test
    void shouldNotCreateTeamWithoutValidTag() throws Exception {
        Team team = getTestTeam();
        team.setTeamTag(null);
        mockMvc.perform(
                        post("/team")
                                .header(HttpHeaders.AUTHORIZATION, token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(team)))
                .andExpect(status().isUnprocessableEntity());

    }


    @Test
    void shouldNotCreateTeamWithoutValidCountry() throws Exception {
        Team team = getTestTeam();
        team.setCountry(null);
        mockMvc.perform(
                        post("/team")
                                .header(HttpHeaders.AUTHORIZATION, token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(team)))
                .andExpect(status().isUnprocessableEntity());

    }

    @Test
    void shouldNotCreateTeamWithoutValidName() throws Exception {
        Team team = getTestTeam();
        team.setTeamName(null);
        mockMvc.perform(
                        post("/team")
                                .header(HttpHeaders.AUTHORIZATION, token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(team)))
                .andExpect(status().isUnprocessableEntity());
    }

    private Team getTestTeam() {
        return new Team(
                null,
                "TEST",
                "TST",
                Collections.emptySet(),
                "TestCountry");
    }


}
