package com.f1.f1results.entities.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class ApplicationUserRepositoryTest {

    public static final String PASSWORD = "password";
    private static final String USERNAME = "username";

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Test
    void itShouldFindByUsername() {
        ApplicationUser user = new ApplicationUser(
                null,
                USERNAME,
                PASSWORD,
                true,
                true,
                true,
                true);
        applicationUserRepository.save(user);
        Optional<ApplicationUser> byUsername = applicationUserRepository.findByUsername(USERNAME);
        assertTrue(byUsername.isPresent());
    }
}