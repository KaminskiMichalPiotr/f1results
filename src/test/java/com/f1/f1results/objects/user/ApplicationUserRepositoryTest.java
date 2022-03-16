package com.f1.f1results.objects.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class ApplicationUserRepositoryTest {

    private final String username = "username";
    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Test
    void itShouldFindByUsername() {
        ApplicationUser user = new ApplicationUser(
                null,
                username,
                "password",
                true,
                true,
                true,
                true);
        applicationUserRepository.save(user);
        Optional<ApplicationUser> byUsername = applicationUserRepository.findByUsername(username);
        assertTrue(byUsername.isPresent());
    }
}