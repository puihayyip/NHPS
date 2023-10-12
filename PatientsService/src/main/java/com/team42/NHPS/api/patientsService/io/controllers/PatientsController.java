/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team42.NHPS.api.patientsService.io.controllers;

import com.team42.NHPS.api.patientsService.data.PatientsEntity;
import com.team42.NHPS.api.patientsService.service.PatientsService;
import com.team42.NHPS.api.patientsService.ui.model.PatientsResponseModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/patients")
public class PatientsController {
    private PatientsService patientsService;

    @Autowired
    public PatientsController(PatientsService patientsService) {
        this.patientsService = patientsService;
    }

    @GetMapping("/{nric}")
    public PatientsResponseModel findByNric(@PathVariable String nric) {
        PatientsEntity patientsEntity = patientsService.findByNric(nric);
        return new ModelMapper().map(patientsEntity, PatientsResponseModel.class);
    }
}
