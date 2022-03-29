package com.f1.f1results.entities.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer implements CommandLineRunner {

    ApplicationUserService applicationUserService;

    @Autowired
    public AdminUserInitializer(ApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }

    @Override
    public void run(String... args) throws Exception {
        ApplicationUser user = new ApplicationUser(
                null,
                "admin",
                "admin",
                true,
                true,
                true,
                true);
        this.applicationUserService.saveUser(user);
    }
}
