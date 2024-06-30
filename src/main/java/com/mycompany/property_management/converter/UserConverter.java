package com.mycompany.property_management.converter;

import com.mycompany.property_management.dto.UserDTO;
import com.mycompany.property_management.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserEntity convertDTOToEnity(UserDTO theUserDTO) {
        UserEntity myUserEntity = new UserEntity();
        myUserEntity.setName(theUserDTO.getName());
        myUserEntity.setEmail(theUserDTO.getEmail());
        myUserEntity.setPhone(theUserDTO.getPhone());
        myUserEntity.setPassword(theUserDTO.getPassword());
        return myUserEntity;
    }

    public UserDTO convertEntityToDTO(UserEntity theUserEntity) {
        UserDTO myUserDTO = new UserDTO();
        myUserDTO.setId(theUserEntity.getId());
        myUserDTO.setName(theUserEntity.getName());
        myUserDTO.setEmail(theUserEntity.getEmail());
        myUserDTO.setPhone(theUserEntity.getPhone());
        return myUserDTO;
    }
}
