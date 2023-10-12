package com.team42.NHPS.api.patientsService.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientsRepository extends CrudRepository<PatientsEntity, String> {
    Optional<PatientsEntity> findByNric(String nric);
}
