package org.springframework.samples.petclinic.service;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.samples.petclinic.model.Adoption;

import org.springframework.samples.petclinic.repository.AdoptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdoptionService {

	private AdoptionRepository adoptionRepository;
	
	
	@Autowired
	public AdoptionService(AdoptionRepository adopRepo, PetService petServ) {
		this.adoptionRepository=adopRepo;
	}
	

	@Transactional
	public void createAdoption(Adoption adoption) {
		adoptionRepository.save(adoption);
	}

	@Transactional(readOnly = true)
	public Collection<Adoption> findAllAdoptions(){
		return adoptionRepository.findAll();
	}
}
