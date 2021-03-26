package org.springframework.samples.petclinic.service;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.PetHotel;
import org.springframework.samples.petclinic.repository.PetHotelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PetHotelService {
	
private PetHotelRepository petHotelRepository;

		@Autowired 
		public PetHotelService(PetHotelRepository petHotelRepository){
			this.petHotelRepository=petHotelRepository;
		}
		@Transactional
		public void savePetHotel(PetHotel ph)  {
			petHotelRepository.save(ph);
		}
		@Transactional (readOnly= true)
		public Collection<PetHotel> findAllPetHotel() throws DataAccessException{
			return (Collection<PetHotel>) petHotelRepository.findAll();
		}
		
}
