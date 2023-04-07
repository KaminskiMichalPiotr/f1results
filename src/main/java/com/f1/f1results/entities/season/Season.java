package com.f1.f1results.entities.season;

import com.f1.f1results.entities.raceevent.RaceEvent;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Season")
@Table(name = "season", uniqueConstraints = @UniqueConstraint(columnNames = "season_year", name = "season_year_unique"))
public class Season {

    public Season(int seasonYear) {
        this.id = null;
        this.seasonYear = seasonYear;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @OneToMany(mappedBy = "season")
    @JsonIgnore
    private List<RaceEvent> raceEvents = new ArrayList<>();

    @Column(nullable = false, name = "season_year")
    @NotNull(message = "Please provide valid season")
    @Min(1950)
    @Max(2050)
    private int seasonYear;
}
