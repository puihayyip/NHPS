/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team42.NHPS.api.patientsService.service;


import com.team42.NHPS.api.patientsService.data.PatientsEntity;
import com.team42.NHPS.api.patientsService.ui.model.PatientsDTO;

public interface PatientsService {
    PatientsEntity savePatientDetails(PatientsDTO patientsDTO);
    PatientsEntity findByNric(String nric);
}
