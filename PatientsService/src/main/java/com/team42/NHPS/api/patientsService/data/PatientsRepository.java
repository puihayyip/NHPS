package com.team42.NHPS.api.patientsService.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientsRepository extends JpaRepository<PatientsEntity, String> {
    Optional<PatientsEntity> findByNric(String nric);
}
