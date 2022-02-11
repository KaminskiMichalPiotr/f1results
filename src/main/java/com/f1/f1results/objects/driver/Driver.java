package com.f1.f1results.objects.driver;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Driver")
@Table(name = "driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotBlank(message = "Please provide driver name and surname")
    private String driver;
    @Column
    @NotBlank(message = "Date of birth must be in following format DD-MM-YYYY")
    @Pattern(regexp = "[0-3][0-9]-[0-1][0-9]-[1-2][0-9][0-9][0-9]",
            message = "Date of birth must be in following format DD-MM-YYYY")
    private String dateOfBirth;
    @Column
    @NotBlank(message = "Nationality cannot be empty")
    private String nationality;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver1 = (Driver) o;
        return driver.equals(driver1.driver) && dateOfBirth.equals(driver1.dateOfBirth) && nationality.equals(driver1.nationality);
    }

}
