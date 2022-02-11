package com.f1.f1results.raceevent;

import com.f1.f1results.location.Location;
import com.f1.f1results.raceresult.RaceResult;
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
    private Long id;

    @ManyToOne(cascade = {
            CascadeType.MERGE
    })
    @JoinColumn(
            name = "location_id",
            referencedColumnName = "id"
    )
    private Location location;

    @OneToOne(cascade = {
            CascadeType.MERGE
    })
    @JoinColumn(
            name = "race_result_id",
            referencedColumnName = "id"
    )
    private RaceResult raceResult;

    //@Column
    //private Season season;

}
