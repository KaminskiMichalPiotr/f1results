package com.f1.f1results.objects.raceevent;

import com.f1.f1results.objects.location.Location;
import com.f1.f1results.objects.raceresult.RaceResult;
import com.f1.f1results.objects.season.Season;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    private RaceResult raceResult;

    @ManyToOne
    @JoinColumn(
            name = "season_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "season_fk")
    )
    private Season season;


}
