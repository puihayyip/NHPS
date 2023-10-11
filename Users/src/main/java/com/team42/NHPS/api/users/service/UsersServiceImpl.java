package com.team42.NHPS.api.users.Users.service;

import com.team42.NHPS.api.users.Users.data.AlbumsServiceClient;
import com.team42.NHPS.api.users.Users.data.UserEntity;
import com.team42.NHPS.api.users.Users.data.UsersRepository;
import com.team42.NHPS.api.users.Users.model.AlbumResponseModel;
import com.team42.NHPS.api.users.Users.shared.UserDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UsersRepository usersRepository;
    private RestTemplate restTemplate;
    private Environment environment;
    private AlbumsServiceClient albumsServiceClient;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UsersServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, UsersRepository usersRepository,
                            RestTemplate restTemplate, Environment environment, AlbumsServiceClient albumsServiceClient) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.usersRepository = usersRepository;
        this.restTemplate = restTemplate;
        this.environment = environment;
        this.albumsServiceClient = albumsServiceClient;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        userDTO.setUserId(UUID.randomUUID().toString());
        userDTO.setEncryptedPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);

        UserEntity returnValue = usersRepository.save(userEntity);

        return modelMapper.map(returnValue, UserDTO.class);
    }

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        UserEntity foundUser = usersRepository.findByEmailAddress(emailAddress);

        if (foundUser == null) throw new UsernameNotFoundException(emailAddress);

        return new User(foundUser.getEmailAddress(), foundUser.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
    }

    @Override
    public UserDTO getUserDetailsByEmailAddress(String emailAddress) {
        UserEntity foundUser = usersRepository.findByEmailAddress(emailAddress);
        if (foundUser == null) throw new UsernameNotFoundException(emailAddress);

        return new ModelMapper().map(foundUser, UserDTO.class);
    }

    @Override
    public UserDTO getUserByUserId(String userId) {

        UserEntity userEntity = usersRepository.findByUserId(userId);
        if (userEntity == null) throw new UsernameNotFoundException("User not found");

        UserDTO userDTO = new ModelMapper().map(userEntity, UserDTO.class);


        List<AlbumResponseModel> albumResponseModelList = null;

        logger.debug("Before calling albums Microservice");
//        String albumsUrl = String.format(environment.getProperty("albums.url"), userId);
//        ResponseEntity<List<AlbumResponseModel>> albumsListResponse = restTemplate.exchange(albumsUrl, HttpMethod.GET, null,
//                new ParameterizedTypeReference<List<AlbumResponseModel>>() {
//                });
//        albumResponseModelList = albumsListResponse.getBody();
        albumResponseModelList = albumsServiceClient.getAlbums(userId);
        logger.debug("After calling albums Microservice");

        userDTO.setAlbumResponseModelList(albumResponseModelList);

        return userDTO;
    }

}
