package com.f1.f1results.objects.raceresult;

import com.f1.f1results.objects.driverresult.DriverResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "RaceResult")
@Table(name = "race_result")
public class RaceResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @OneToMany
    @JoinColumn(
            name = "race_result_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "race_result_id")
    )
    private List<DriverResult> driverResults = Collections.emptyList();

}
