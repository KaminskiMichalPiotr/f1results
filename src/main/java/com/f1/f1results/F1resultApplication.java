package com.f1.f1results;

import com.f1.f1results.objects.user.ApplicationUser;
import com.f1.f1results.objects.user.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ConfigurationPropertiesScan
public class F1resultApplication {

	public static void main(String[] args) {
		SpringApplication.run(F1resultApplication.class, args);
	}

	ApplicationUserService applicationUserService;

	@Autowired
	public F1resultApplication(ApplicationUserService applicationUserService) {
		this.applicationUserService = applicationUserService;
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {
			ApplicationUser user = new ApplicationUser(
					null,
					"krzysiu",
					"12345",
					true,
					true,
					true,
					true);
			this.applicationUserService.saveUser(user);
		};
	}

}
