package com.f1.f1results.driver;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Driver")
@Table(name = "driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String driver;
    @Column
    private Date dateOfBirth;
    @Column
    private String nationality;

}
