package com.team42.NHPS.api.usersService.model;

import lombok.Data;

import java.util.Date;


@Data
public class PatientsResponseModel {
    private String nric;
    private String name;
    private String address;
    private String gender;
    private Date dob;

}