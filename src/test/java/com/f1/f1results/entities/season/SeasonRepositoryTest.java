package com.f1.f1results.entities.season;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class SeasonRepositoryTest {

    private static final int SEASON_YEAR = 2020;

    @Autowired
    private SeasonRepository seasonRepository;

    @Test
    void itShouldFindBySeasonYear() {
        Season season = new Season(null, Collections.emptyList(), SEASON_YEAR);
        seasonRepository.save(season);
        Optional<Season> bySeasonYear = seasonRepository.findBySeasonYear(SEASON_YEAR);
        assertTrue(bySeasonYear.isPresent());
    }
}