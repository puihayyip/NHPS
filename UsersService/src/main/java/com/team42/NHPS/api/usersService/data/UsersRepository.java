package com.team42.NHPS.api.usersService.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByEmailAddress(String emailAddress);
    UserEntity findByUserId(String userId);
}
