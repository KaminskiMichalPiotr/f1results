package com.f1.f1results.objects.season;

import com.f1.f1results.objects.calendar.Calendar;
import com.f1.f1results.objects.raceevent.RaceEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Season")
@Table(name = "season")
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<RaceEvent> raceEvents;
    @OneToOne
    @JoinColumn(
            name = "calendar_id",
            referencedColumnName = "id"
    )
    private Calendar calendar;
}
