package com.f1.f1results.driverresult;

import com.f1.f1results.driver.Driver;
import com.f1.f1results.team.Team;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DriverResult {

    private Driver driver;
    private int position;
    private int points;
    private Team team;

}
