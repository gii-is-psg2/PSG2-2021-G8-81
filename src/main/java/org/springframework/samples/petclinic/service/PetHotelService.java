package org.springframework.samples.petclinic.service;


import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.PetHotel;
import org.springframework.samples.petclinic.repository.PetHotelRepository;
import org.springframework.samples.petclinic.service.exceptions.TwoPetsBookingException;
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
		public void saveCita(PetHotel petHotel) throws DataAccessException,TwoPetsBookingException {
			List<PetHotel> list = (List<PetHotel>) findPetsUpToDate();
			for(int i = 0;list.size()>i; i++) {
				if(list.get(i).getPet().equals(petHotel.getPet())) {
					throw new TwoPetsBookingException();
					
				}
			}
				petHotelRepository.save(petHotel);	
		}
		@Transactional(readOnly = true)
		public Collection<PetHotel> findAllPetHotel() throws DataAccessException {
			return (Collection<PetHotel>) petHotelRepository.findAll();
		}
		
		@Transactional(readOnly = true )
		public Collection<PetHotel> findPetsUpToDate() throws DataAccessException{
			return (Collection<PetHotel>) petHotelRepository.findAll().stream().filter(x->x.getDateExit().isAfter(LocalDate.now())).collect(Collectors.toList());
		}
		
}
