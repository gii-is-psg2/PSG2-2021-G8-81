package org.springframework.samples.petclinic.repository;

import java.util.Collection;


import org.springframework.samples.petclinic.model.PetHotel;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

public interface PetHotelRepository extends CrudRepository<PetHotel, Integer>{

public Collection<PetHotel> findAll() throws DataAccessException;
}
