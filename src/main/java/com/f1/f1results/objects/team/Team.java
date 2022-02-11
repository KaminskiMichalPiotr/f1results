package com.f1.f1results.objects.team;

import com.f1.f1results.objects.driver.Driver;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Team")
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Long id;

    @Column
    @NotBlank(message = "Team name cannot be empty!")
    private String teamName;

    @Column
    @NotBlank(message = "Team tag cannot be empty!")
    private String teamTag;

    @ManyToMany
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
    private Set<Driver> drivers = new HashSet<>();

    @Column
    @NotBlank(message = "Country cannot be empty!")
    private String country;

}

