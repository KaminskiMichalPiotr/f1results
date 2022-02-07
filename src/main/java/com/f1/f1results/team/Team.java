package com.f1.f1results.team;

import com.f1.f1results.driver.Driver;
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
@Entity(name = "Team")
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String teamName;
    @Column
    private String teamTag;
    @ManyToMany
    private Set<Driver> drivers;
    @Column
    private String country;

}
