package com.team42.NHPS.api.users.controller;

import com.team42.NHPS.api.users.model.CreateUserResponseModel;
import com.team42.NHPS.api.users.model.UserDetail;
import com.team42.NHPS.api.users.model.UserResponseModel;
import com.team42.NHPS.api.users.service.UsersService;
import com.team42.NHPS.api.users.shared.UserDTO;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private Environment environment;
    UsersService usersService;

    @Autowired
    public UsersController(Environment environment, UsersService usersService) {
        this.environment = environment;
        this.usersService = usersService;
    }

    @GetMapping("/status")
    public String status() {
        return "Working on port " + environment.getProperty("local.server.port") + "\nToken = " + environment.getProperty("token.secret");
    }

    @PostMapping
    public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody UserDetail usersDetail) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDTO userDTO = modelMapper.map(usersDetail, UserDTO.class);
        UserDTO createdUser = usersService.createUser(userDTO);

        CreateUserResponseModel returnValue = modelMapper.map(createdUser, CreateUserResponseModel.class);
        return new ResponseEntity<>(returnValue, HttpStatus.CREATED);
    }

    @GetMapping(value = "{userId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserResponseModel> getUser(@PathVariable("userId") String userId) {

        UserDTO userDTO = usersService.getUserByUserId(userId);

        return ResponseEntity.status(HttpStatus.OK).body(new ModelMapper().map(userDTO, UserResponseModel.class));
    }

}
