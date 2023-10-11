package com.team42.NHPS.api.users.Users.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestModel {
    private String emailAddress;
    private String password;
}
