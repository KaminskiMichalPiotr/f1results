package com.f1.f1results.driverresult;

import com.f1.f1results.driver.Driver;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "DriverResult")
@Table(name = "driver_result")
public class DriverResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Driver driver;
    @Column
    private int position;
    @Column
    private int points;

    //private Team team;

}
