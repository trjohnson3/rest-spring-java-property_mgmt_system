package com.mycompany.property_management.controller;

import com.mycompany.property_management.dto.PropertyDTO;
import com.mycompany.property_management.dto.UserDTO;
import com.mycompany.property_management.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO theUserDTO) {
        theUserDTO = userService.register(theUserDTO);
        return new ResponseEntity<UserDTO>(
                theUserDTO,
                HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO theUserDTO) {
        theUserDTO = userService.login(theUserDTO.getEmail(), theUserDTO.getPassword());
        return new ResponseEntity<UserDTO>(
                theUserDTO,
                HttpStatus.OK);
    }
}
