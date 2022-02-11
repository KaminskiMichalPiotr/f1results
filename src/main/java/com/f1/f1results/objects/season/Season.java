package com.f1.f1results.objects.season;

import com.f1.f1results.objects.calendar.Calendar;
import com.f1.f1results.objects.raceevent.RaceEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
    @Column(updatable = false)
    private Long id;

    @OneToMany(mappedBy = "season")
    private List<RaceEvent> raceEvents;

    @OneToOne
    @JoinColumn(
            name = "calendar_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "calendar_fk")
    )
    private Calendar calendar;

    @Column(nullable = false)
    @NotNull(message = "Please provide valid season")
    @Min(1950)
    @Max(2050)
    private int seasonYear;
}
