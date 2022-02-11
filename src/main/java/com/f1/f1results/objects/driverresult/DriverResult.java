package com.f1.f1results.objects.driverresult;

import com.f1.f1results.objects.driver.Driver;
import com.f1.f1results.objects.team.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "DriverResult")
@Table(name = "driver_result")
public class DriverResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Driver driver;
    @Column
    @NotNull(message = "Position cannot be empty")
    @Min(value = 1, message = "Position should be in range 1-24")
    @Max(value = 24, message = "Position should be in range 1-24")
    private int position;
    @Column
    private double points;
    @ManyToOne
    private Team team;

}
