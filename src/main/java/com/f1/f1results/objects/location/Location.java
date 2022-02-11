package com.f1.f1results.objects.location;

import com.f1.f1results.objects.calendar.Calendar;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Entity(name = "Location")
@Table(name = "location")
@JsonIgnoreProperties("locationExistingInCalendars")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Location cannot be empty!")
    private String location;

    @Column(nullable = false)
    @NotBlank(message = "Location tag cannot be empty!")
    private String locationTag;

    @Column(nullable = false)
    @NotBlank(message = "Country cannot be empty!")
    private String country;

    @ManyToMany(mappedBy = "locations")
    private Set<Calendar> locationExistingInCalendars = new HashSet<>();

}
