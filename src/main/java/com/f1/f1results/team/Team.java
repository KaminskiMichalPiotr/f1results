package com.f1.f1results.team;

import com.f1.f1results.driver.Driver;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    private String teamName;
    private String teamTag;
    private Set<Driver> drivers;
    private String country;

}
