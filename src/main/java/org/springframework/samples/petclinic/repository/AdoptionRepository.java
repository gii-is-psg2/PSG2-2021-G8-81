package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Adoption;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptionRepository extends CrudRepository<Adoption, Integer>{

	Adoption findById(int id) throws DataAccessException;
	

	public Collection<Adoption> findAll() throws DataAccessException;
	
	@Query("SELECT adop FROM Adoption adop where adop.pet.id=:petId")
	public List<Adoption> findPetById(int petId) throws DataAccessException;

}
