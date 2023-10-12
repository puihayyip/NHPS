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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("/api/patients")
public class PatientsController {
    private PatientsService patientsService;
    private Logger logger;

    @Autowired
    public PatientsController(PatientsService patientsService, Logger logger) {
        this.patientsService = patientsService;
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    @GetMapping("/{nric}")
    public PatientsResponseModel findByNric(@PathVariable String nric) {
        PatientsEntity patientsEntity = patientsService.findByNric(nric);
        return new ModelMapper().map(patientsEntity, PatientsResponseModel.class);
    }
}
