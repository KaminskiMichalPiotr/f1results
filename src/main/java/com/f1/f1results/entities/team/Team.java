package com.f1.f1results.entities.team;

import com.f1.f1results.entities.driver.Driver;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Team")
@Table(name = "team", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"team_name"}, name = "unique_team_name"),
        @UniqueConstraint(columnNames = {"team_tag"}, name = "unique_team_tag")
})
public class Team {

    public Team(String teamName, String teamTag, String country) {
        this.id = null;
        this.teamName = teamName;
        this.teamTag = teamTag;
        this.country = country;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Long id;

    @Column(name = "team_name")
    @NotBlank(message = "Team name cannot be empty!")
    private String teamName;


    //TODO: tag min max 3
    @Column(name = "team_tag")
    @NotBlank(message = "Team tag cannot be empty!")
    private String teamTag;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "drivers_in_teams",
            joinColumns = @JoinColumn(
                    name = "team_id",
                    foreignKey = @ForeignKey(name = "team_fk")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "driver_id",
                    foreignKey = @ForeignKey(name = "driver_fk")
            )
    )
    @JsonIgnore
    private Set<Driver> drivers = new HashSet<>();

    @Column
    @NotBlank(message = "Country cannot be empty!")
    private String country;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(teamName, team.teamName) && Objects.equals(teamTag, team.teamTag) && Objects.equals(country, team.country);
    }

    public void addDriver(Driver driver) {
        getDrivers().add(driver);
    }

    public void removeDriver(Driver driver) {
        getDrivers().remove(driver);
    }

}

