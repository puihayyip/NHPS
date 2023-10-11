package com.team42.NHPS.api.users.shared;

import com.team42.NHPS.api.users.model.AlbumResponseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -6797874165207331687L;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private String userId;
    private String encryptedPassword;
    private List<AlbumResponseModel> albumResponseModelList;
}
