package com.f1.f1results.location;

import com.f1.f1results.calendar.Calendar;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Location")
@Table(name = "location")
@JsonIgnoreProperties("locationExistingInCalendars")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String location;
    @Column
    private String locationTag;
    @Column
    private String country;
    @ManyToMany(mappedBy = "locations")
    private Set<Calendar> locationExistingInCalendars;

}
