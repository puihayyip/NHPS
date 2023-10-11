package com.team42.NHPS.api.users.Users.model;

import lombok.Data;

import java.util.List;

@Data
public class UserResponseModel {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private List<AlbumResponseModel> albumResponseModelList;
}
