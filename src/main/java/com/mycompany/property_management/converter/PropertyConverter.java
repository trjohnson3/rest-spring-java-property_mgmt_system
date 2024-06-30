package com.mycompany.property_management.converter;

import com.mycompany.property_management.dto.PropertyDTO;
import com.mycompany.property_management.entity.PropertyEntity;
import org.springframework.stereotype.Component;

@Component
public class PropertyConverter {

    public PropertyEntity convertDTOToEntity(PropertyDTO thePropertyDTO) {
        PropertyEntity myPropertyEntity = new PropertyEntity();
        myPropertyEntity.setTitle(thePropertyDTO.getTitle());
        myPropertyEntity.setDescription(thePropertyDTO.getDescription());
        myPropertyEntity.setAddress(thePropertyDTO.getAddress());
        myPropertyEntity.setPrice(thePropertyDTO.getPrice());
        return myPropertyEntity;
    }

    public PropertyDTO convertEntityToDTO(PropertyEntity thePropertyEntity) {
        PropertyDTO myPropertyDTO = new PropertyDTO();
        myPropertyDTO.setId(thePropertyEntity.getId());
        myPropertyDTO.setAddress(thePropertyEntity.getAddress());
        myPropertyDTO.setDescription(thePropertyEntity.getDescription());
        myPropertyDTO.setTitle(thePropertyEntity.getTitle());
        myPropertyDTO.setPrice(thePropertyEntity.getPrice());
        return myPropertyDTO;
    }
}
