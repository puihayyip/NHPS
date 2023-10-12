/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team42.NHPS.api.patientsService.service;

import com.team42.NHPS.api.patientsService.data.PatientsEntity;

import com.team42.NHPS.api.patientsService.data.PatientsRepository;
import com.team42.NHPS.api.patientsService.exception.ResourceNotFoundException;
import com.team42.NHPS.api.patientsService.ui.model.PatientsDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientsServiceImpl implements PatientsService {
    private final PatientsRepository patientsRepository;

    @Autowired
    public PatientsServiceImpl(PatientsRepository patientsRepository) {
        this.patientsRepository = patientsRepository;
    }

    @Override
    public PatientsEntity savePatientDetails(PatientsDTO patientsDTO) {
        PatientsEntity patientsEntity;
        try {
            patientsEntity = patientsRepository.save(new ModelMapper().map(patientsDTO, PatientsEntity.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return patientsEntity;
    }

    @Override
    public PatientsEntity findByNric(String nric) {
        return patientsRepository.findByNric(nric).orElseThrow(() -> new ResourceNotFoundException("PatientsSevice", "nric", nric));
    }

}
