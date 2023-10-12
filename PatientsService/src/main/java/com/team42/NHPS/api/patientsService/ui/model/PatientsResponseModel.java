package com.team42.NHPS.api.patientsService.ui.model;

import lombok.Data;

import java.util.Date;


@Data
public class PatientsResponseModel {
    private String nric;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String emailAddress;
    private String gender;
    private Date dob;

}
