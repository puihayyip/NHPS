package com.team42.NHPS.api.usersService.data;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity implements Serializable {
    @Serial
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private static final long serialVersionUID = 4733264403966435793L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50,name = "FIRST_NAME")
    private String firstName;

    @Column(nullable = false, length = 50, name = "LAST_NAME")
    private String lastName;

    @Column(nullable = false, length = 120, unique = true, name = "EMAIL_ADDRESS")
    private String emailAddress;

    @Column(nullable = false, unique = true, name = "USER_ID")
    private String userId;

    @Column(nullable = false, unique = true, name = "ENCRYPTED_PASSWORD")
    private String encryptedPassword;
}
