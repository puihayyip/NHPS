package com.team42.NHPS.api.patientsService.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serial;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patients")
public class PatientsEntity {
    @Serial
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private static final long serialVersionUID = 4733264403966435793L;
    @Id
    private String nric;
    @Column(nullable = false, length = 50,name = "FIRST_NAME")
    private String firstName;
    @Column(nullable = false, length = 50,name = "LAST_NAME")
    private String lastName;
    @Column(nullable = false, length = 50,name = "ADDRESS")
    private String address;
    @Column(nullable = false, length = 50,name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(nullable = false, length = 50,name = "EMAIL_ADDRESS")
    private String emailAddress;
    @Column(nullable = false, length = 50,name = "GENDER")
    private String gender;
    @Column(nullable = false, length = 50,name = "DOB")
    private Date dob;

}
