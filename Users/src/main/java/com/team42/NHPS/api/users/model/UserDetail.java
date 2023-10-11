package com.team42.NHPS.api.users.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetail {

    @NotNull(message = "First name must not be null")
    @Size(min = 2, message = "First name must not be less than two characters")
    private String firstName;

    @NotNull(message = "Last name must not be null")
    @Size(min = 2, message = "Last name must not be less than two characters")
    private String lastName;

    @NotNull(message = "Email must not be null")
    @Email(message = "Invalid email address")
    private String emailAddress;

    @NotNull(message = "Password must not be null")
    @Size(min = 8, max = 16, message = "Password must be between 8 and 16 characters")
    private String password;

}
