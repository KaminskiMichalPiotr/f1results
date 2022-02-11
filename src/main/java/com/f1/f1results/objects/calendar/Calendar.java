package com.f1.f1results.objects.calendar;

import com.f1.f1results.objects.location.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Calendar")
@Table(name = "calendar")
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @ManyToMany(
            cascade = {
                    CascadeType.MERGE, CascadeType.PERSIST
            }
    )
    @JoinTable(
            name = "locations_in_calendar",
            joinColumns = @JoinColumn(
                    name = "calendar_id",
                    foreignKey = @ForeignKey(name = "calendar_fk")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "location_id",
                    foreignKey = @ForeignKey(name = "location_fk")
            )
    )
    private List<Location> locations = new ArrayList<>();

    public void addLocation(Location location) {
        getLocations().add(location);
        location.getLocationExistingInCalendars().add(this);
    }

    public void removeLocation(Location location) {
        getLocations().remove(location);
        location.getLocationExistingInCalendars().remove(this);
    }

}
