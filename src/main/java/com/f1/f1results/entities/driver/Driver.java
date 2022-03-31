package com.f1.f1results.entities.driver;

import com.f1.f1results.entities.team.Team;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Driver")
@Table(name = "driver")
public class Driver {

    public Driver(String driver, String dateOfBirth, String nationality) {
        this.driver = driver;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id = null;

    @Column
    @NotBlank(message = "Please provide driver name and surname")
    private String driver;

    @Column
    @Pattern(regexp = "[0-3][0-9]-[0-1][0-9]-[1-2][0-9][0-9][0-9]",
            message = "Date of birth must be in following format DD-MM-YYYY")
    private String dateOfBirth;

    @Column
    @NotBlank(message = "Nationality cannot be empty")
    private String nationality;

    @ManyToMany(mappedBy = "drivers")
    private Set<Team> teams = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver1 = (Driver) o;
        return driver.equals(driver1.driver) && dateOfBirth.equals(driver1.dateOfBirth) && nationality.equals(driver1.nationality);
    }

}
