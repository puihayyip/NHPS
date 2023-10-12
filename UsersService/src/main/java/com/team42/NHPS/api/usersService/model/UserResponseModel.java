package com.team42.NHPS.api.usersService.model;

import lombok.Data;

@Data
public class UserResponseModel {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
}
