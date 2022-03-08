package com.f1.f1results.objects.driverresult;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverResultRepository extends JpaRepository<DriverResult, Long> {
}
