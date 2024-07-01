package com.mycompany.property_management.controller;

import com.mycompany.property_management.dto.PropertyDTO;
import com.mycompany.property_management.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/properties")
    public ResponseEntity<List<PropertyDTO>> getAllProperties() {
        List<PropertyDTO> propertyList = propertyService.getAllProperties();
        return new ResponseEntity<List<PropertyDTO>>(
                propertyList,
                HttpStatus.OK);
    }

    @GetMapping("/properties/{thePropertyId}")
    public ResponseEntity<PropertyDTO> getPropertyById(
            @PathVariable("thePropertyId") Long thePropertyId) {
        PropertyDTO dto = propertyService.getPropertyById(thePropertyId);
        if (dto.getId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<PropertyDTO>(
                dto,
                HttpStatus.OK
        );
    }

    @GetMapping("/properties/users/{userId}")
    public ResponseEntity<List<PropertyDTO>> getAllPropertiesForUser(@PathVariable("userId") Long userId) {
        List<PropertyDTO> propertyList = propertyService.getAllPropertiesByUser(userId);
        return new ResponseEntity<List<PropertyDTO>>(
                propertyList,
                HttpStatus.OK);
    }

    @PostMapping("/properties")
    public ResponseEntity<PropertyDTO> createProperty(@RequestBody PropertyDTO thePropertyDTO) {
        thePropertyDTO = propertyService.saveProperty(thePropertyDTO);
        return new ResponseEntity<PropertyDTO>(
                thePropertyDTO,
                HttpStatus.CREATED);
    }

    @PutMapping("/properties/{thePropertyId}")
    public ResponseEntity<PropertyDTO> updateProperty(
            @RequestBody PropertyDTO thePropertyDTO,
            @PathVariable("thePropertyId") Long thePropertyId) {
        thePropertyDTO = propertyService.updateProperty(thePropertyDTO, thePropertyId);
        return new ResponseEntity<PropertyDTO>(
                thePropertyDTO,
                HttpStatus.CREATED);
    }

    @PatchMapping("/properties/{thePropertyId}/description")
    public ResponseEntity<PropertyDTO> updatePropertyDescription(
            @RequestBody PropertyDTO thePropertyDTO,
            @PathVariable("thePropertyId") Long thePropertyId) {
        thePropertyDTO = propertyService.updatePropertyDescription(thePropertyDTO, thePropertyId);
        return new ResponseEntity<PropertyDTO>(
                thePropertyDTO,
                HttpStatus.OK);
    }

    @PatchMapping("/properties/{thePropertyId}/price")
    public ResponseEntity<PropertyDTO> updatePropertyPrice(
            @RequestBody PropertyDTO thePropertyDTO,
            @PathVariable("thePropertyId") Long thePropertyId) {
        thePropertyDTO = propertyService.updatePropertyPrice(thePropertyDTO, thePropertyId);
        return new ResponseEntity<PropertyDTO>(
                thePropertyDTO,
                HttpStatus.OK);
    }

    @DeleteMapping("/properties/{thePropertyId}")
    public ResponseEntity<Void> deleteProperty(@PathVariable("thePropertyId") Long thePropertyId) {
        propertyService.deleteProperty(thePropertyId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
