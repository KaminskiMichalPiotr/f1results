package com.f1.f1results.entities.driverresult;

import com.f1.f1results.entities.driver.Driver;
import com.f1.f1results.entities.team.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "DriverResult")
@Table(name = "driver_result")
public class DriverResult {

    @Column
    @NotNull(message = "Position cannot be empty")
    @Min(value = 1, message = "Position should be in range 1-30")
    private int position;
    @Column
    private int sprintRacePosition = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "driver_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "driver_fk")
    )
    private Driver driver;
    @Column
    private boolean fastestLap = false;

    @Column
    private double points;

    @ManyToOne
    @JoinColumn(
            name = "team_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "team_fk")
    )
    private Team team;

    public DriverResult(Driver driver, int position, double points, Team team) {
        this.id = null;
        this.driver = driver;
        this.position = position;
        this.points = points;
        this.team = team;
    }

    public DriverResult(int position, boolean fastestLap, Driver driver, int sprintRacePosition, double points, Team team) {
        this.position = position;
        this.fastestLap = fastestLap;
        this.driver = driver;
        this.sprintRacePosition = sprintRacePosition;
        this.points = points;
        this.team = team;
    }

    public DriverResult(Long id, Driver driver, int position, double points, Team team, int sprintRacePosition, boolean fastestLap) {
        this.position = position;
        this.sprintRacePosition = sprintRacePosition;
        this.id = id;
        this.driver = driver;
        this.fastestLap = fastestLap;
        this.points = points;
        this.team = team;
    }

    public boolean hasFastestLap() {
        return this.fastestLap;
    }

}
