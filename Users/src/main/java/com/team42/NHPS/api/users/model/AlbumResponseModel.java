package com.team42.NHPS.api.users.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AlbumResponseModel {

    private String albumId;
    private String userId;
    private String name;
    private String description;
}
