package com.f1.f1results.raceevent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceEventRepository extends JpaRepository<RaceEvent, Long> {

}
