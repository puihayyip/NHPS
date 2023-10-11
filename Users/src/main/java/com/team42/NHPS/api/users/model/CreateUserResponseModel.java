package com.team42.NHPS.api.users.Users.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResponseModel {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String userId;

}
