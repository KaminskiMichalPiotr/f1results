package com.f1.f1results.raceevent;

import com.f1.f1results.location.Location;
import com.f1.f1results.raceresult.RaceResult;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RaceEvent {

    private Location location;
    private RaceResult raceResult;

}
