package com.team42.NHPS.api.patientsService.ui.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Date;


@Data
public class PatientsDTO {
    @NotBlank
    private String nric;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String address;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    @Email
    private String emailAddress;
    @Pattern(regexp = "^[mf]$")
    private String gender;
    @NotBlank
    private Date dob;

}
