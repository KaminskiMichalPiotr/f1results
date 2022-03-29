package com.f1.f1results.entities.location;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Location")
@Table(name = "location", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"location_tag"}, name = "unique_location_tag"),
})
@JsonIgnoreProperties("locationExistingInCalendars")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, name = "id")
    private Long id;

    @Column(nullable = false, name = "location")
    @NotBlank(message = "Location cannot be empty!")
    private String location;

    @Column(nullable = false, name = "location_tag")
    @Size(min = 3, max = 3)
    @NotBlank(message = "Location tag cannot be empty!")
    private String locationTag;

    @Column(nullable = false, name = "country")
    @NotBlank(message = "Country cannot be empty!")
    private String country;


}
