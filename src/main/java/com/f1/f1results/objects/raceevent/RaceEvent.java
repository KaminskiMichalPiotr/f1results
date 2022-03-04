package com.f1.f1results.objects.raceevent;

import com.f1.f1results.objects.location.Location;
import com.f1.f1results.objects.raceresult.RaceResult;
import com.f1.f1results.objects.season.Season;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "RaceEvent")
@Table(name = "race_event")
public class RaceEvent {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @ManyToOne(cascade = {
            CascadeType.MERGE
    })
    @JoinColumn(
            name = "location_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "location_fk")
    )
    private Location location;

    @OneToOne(cascade = {
            CascadeType.MERGE
    })
    @JoinColumn(
            name = "race_result_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "race_result_fk")
    )
    @JsonIgnore
    private RaceResult raceResult;

    @ManyToOne
    @JoinColumn(
            name = "season_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "season_fk")
    )
    @JsonIgnore
    private Season season;

    @Column
    @NotBlank(message = "Date of reace must be in following format DD-MM-YYYY")
    @Pattern(regexp = "[0-3][0-9]-[0-1][0-9]-[1-2][0-9][0-9][0-9]",
            message = "Date of race must be in following format DD-MM-YYYY")
    private String dateOfRace;

    @Column
    @NotNull(message = "Index of the race event must be provided")
    private int index;


}
