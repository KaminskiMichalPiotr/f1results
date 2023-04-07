package com.f1.f1results.entities.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Component responsible for creating initial admin user
 * when application is first bootstrap.
 *
 * @username parameter is set in application.properties
 * @password parameter is set in application.properties
 */
@Component
public class AdminUserInitializer implements CommandLineRunner {

    private final ApplicationUserService applicationUserService;
    @Value("${admin.username}")
    private String username;
    @Value("${admin.password}")
    private String password;

    @Autowired
    public AdminUserInitializer(ApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }

    @Override
    public void run(String... args) throws Exception {
        ApplicationUser user = new ApplicationUser(
                username,
                password);
        this.applicationUserService.saveUser(user);
    }
}
