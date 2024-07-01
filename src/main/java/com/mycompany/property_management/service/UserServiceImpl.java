package com.mycompany.property_management.service;

import com.mycompany.property_management.converter.UserConverter;
import com.mycompany.property_management.dto.UserDTO;
import com.mycompany.property_management.entity.UserEntity;
import com.mycompany.property_management.exception.BusinessException;
import com.mycompany.property_management.exception.ErrorModel;
import com.mycompany.property_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO register(UserDTO theUserDTO) {

        Optional<UserEntity> myOptionalUserEntity = userRepository.findByEmail(
                theUserDTO.getEmail());
        if(myOptionalUserEntity.isPresent()) {
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("EMAIL_ALREADY_EXISTS");
            errorModel.setMessage("The email you are trying to register already exists.");
            errorModelList.add(errorModel);

            throw new BusinessException(errorModelList);
        } else {
            UserEntity myUserEntity = userConverter.convertDTOToEnity(theUserDTO);
            myUserEntity = userRepository.save(myUserEntity);
            theUserDTO = userConverter.convertEntityToDTO(myUserEntity);
        }
        return theUserDTO;
    }

    @Override
    public UserDTO login(String theEmail, String thePassword) {

        UserDTO myUserDTO= null;
        Optional<UserEntity> myOptionalUserEntity = userRepository.findByEmailAndPassword(
                theEmail, thePassword);
        if(myOptionalUserEntity.isPresent()) {
            myUserDTO = userConverter.convertEntityToDTO(myOptionalUserEntity.get());
        } else {
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("INVALID_LOGIN");
            errorModel.setMessage("Invalid email or password");
            errorModelList.add(errorModel);

            throw new BusinessException(errorModelList);
        }
        return myUserDTO;
    }
}
