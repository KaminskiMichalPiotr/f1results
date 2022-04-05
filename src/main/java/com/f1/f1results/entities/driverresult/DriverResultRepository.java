package com.f1.f1results.entities.driverresult;

import com.f1.f1results.entities.driver.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverResultRepository extends JpaRepository<DriverResult, Long> {

    List<DriverResult> findDriverResultsByDriver(Driver driver);

}
