package com.mycompany.property_management.service;

import com.mycompany.property_management.converter.PropertyConverter;
import com.mycompany.property_management.dto.PropertyDTO;
import com.mycompany.property_management.entity.PropertyEntity;
import com.mycompany.property_management.entity.UserEntity;
import com.mycompany.property_management.exception.BusinessException;
import com.mycompany.property_management.exception.ErrorModel;
import com.mycompany.property_management.repository.PropertyRepository;
import com.mycompany.property_management.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService{

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyConverter propertyConverter;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public PropertyDTO saveProperty(PropertyDTO thePropertyDTO) {

        Optional<UserEntity> optionalUserEntity = userRepository.findById(thePropertyDTO.getUserId());
        if(optionalUserEntity.isPresent()) {
            PropertyEntity pe = propertyConverter.convertDTOToEntity((thePropertyDTO));
            pe.setUserEntity(optionalUserEntity.get());
            pe = propertyRepository.save(pe);

            thePropertyDTO = propertyConverter.convertEntityToDTO(pe);
        } else {
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("USER_ID_NON_EXISTENT");
            errorModel.setMessage("The user id does not exist in the database");
            errorModelList.add(errorModel);

            throw new BusinessException(errorModelList);
        }

        return thePropertyDTO;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {

        List<PropertyEntity> propertyEntityList = (List<PropertyEntity>) propertyRepository.findAll();
        List<PropertyDTO> propertyDTOList = new ArrayList<>();

        for(PropertyEntity pe : propertyEntityList) {
            PropertyDTO dto = propertyConverter.convertEntityToDTO(pe);
            propertyDTOList.add(dto);
        }

        return propertyDTOList;
    }

    @Override
    public List<PropertyDTO> getAllPropertiesByUser(Long userId) {

        List<PropertyEntity> propertyEntityList = (List<PropertyEntity>) propertyRepository
                .findAllByUserEntityId(userId);
        List<PropertyDTO> propertyDTOList = new ArrayList<>();

        for(PropertyEntity pe : propertyEntityList) {
            PropertyDTO dto = propertyConverter.convertEntityToDTO(pe);
            propertyDTOList.add(dto);
        }

        return propertyDTOList;
    }

    @Override
    public PropertyDTO getPropertyById(Long propertyId) {

        Optional<PropertyEntity> optionalPropertyEntity = propertyRepository.findById(propertyId);
        PropertyDTO dto = new PropertyDTO();

        if(optionalPropertyEntity.isPresent()) {
            PropertyEntity pe = optionalPropertyEntity.get();
            dto = propertyConverter.convertEntityToDTO(pe);
        }

        return dto;
    }

    @Override
    @Transactional
    public PropertyDTO updateProperty(PropertyDTO thePropertyDTO, Long thePropertyId) {

        Optional<PropertyEntity> optionalPropertyEntity = propertyRepository.findById(thePropertyId);

        if(optionalPropertyEntity.isPresent()) {
            PropertyEntity pe = optionalPropertyEntity.get();
            pe.setTitle(thePropertyDTO.getTitle());
            pe.setDescription(thePropertyDTO.getDescription());
            pe.setAddress(thePropertyDTO.getAddress());
            pe.setPrice(thePropertyDTO.getPrice());

            pe = propertyRepository.save(pe);
            thePropertyDTO = propertyConverter.convertEntityToDTO(pe);
        }

        return thePropertyDTO;
    }

    @Override
    @Transactional
    public PropertyDTO updatePropertyDescription(PropertyDTO thePropertyDTO, Long thePropertyId) {

        Optional<PropertyEntity> optionalPropertyEntity = propertyRepository.findById(thePropertyId);

        if(optionalPropertyEntity.isPresent()) {
            PropertyEntity pe = optionalPropertyEntity.get();
            pe.setDescription(thePropertyDTO.getDescription());

            pe = propertyRepository.save(pe);
            thePropertyDTO = propertyConverter.convertEntityToDTO(pe);
        }

        return thePropertyDTO;
    }

    @Override
    @Transactional
    public PropertyDTO updatePropertyPrice(PropertyDTO thePropertyDTO, Long thePropertyId) {

        Optional<PropertyEntity> optionalPropertyEntity = propertyRepository.findById(thePropertyId);

        if(optionalPropertyEntity.isPresent()) {
            PropertyEntity pe = optionalPropertyEntity.get();
            pe.setPrice(thePropertyDTO.getPrice());

            pe = propertyRepository.save(pe);
            thePropertyDTO = propertyConverter.convertEntityToDTO(pe);
        }

        return thePropertyDTO;
    }

    @Override
    @Transactional
    public void deleteProperty(Long thePropertyId) {

        propertyRepository.deleteById(thePropertyId);
    }
}
