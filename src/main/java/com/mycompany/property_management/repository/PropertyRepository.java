package com.mycompany.property_management.repository;

import com.mycompany.property_management.entity.PropertyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends CrudRepository<PropertyEntity, Long> {

    List<PropertyEntity> findAllByUserEntityId(Long userEntityId);
}
