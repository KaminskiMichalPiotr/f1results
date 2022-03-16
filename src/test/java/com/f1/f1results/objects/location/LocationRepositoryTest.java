package com.f1.f1results.objects.location;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class LocationRepositoryTest {

    private final String tag = "XXX";
    @Autowired
    private LocationRepository underTest;

    @Test
    void itShouldFindByLocationTag() {
        Location location = new Location(null, "Test", tag, "Test");
        underTest.save(location);
        Optional<Location> byLocationTag = underTest.findByLocationTag(tag);
        assertTrue(byLocationTag.isPresent());
    }

}