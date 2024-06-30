package com.mycompany.property_management.service;

import com.mycompany.property_management.converter.UserConverter;
import com.mycompany.property_management.dto.UserDTO;
import com.mycompany.property_management.entity.UserEntity;
import com.mycompany.property_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO register(UserDTO theUserDTO) {
        UserEntity myUserEntity = userConverter.convertDTOToEnity(theUserDTO);
        myUserEntity = userRepository.save(myUserEntity);
        theUserDTO = userConverter.convertEntityToDTO(myUserEntity);
        return theUserDTO;
    }

    @Override
    public UserDTO login(String theEmail, String thePassword) {
        return null;
    }
}
