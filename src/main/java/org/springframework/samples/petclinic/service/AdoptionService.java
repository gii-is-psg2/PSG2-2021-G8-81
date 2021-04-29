package org.springframework.samples.petclinic.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
	@Transactional(readOnly = true)
	public Adoption findAdoptionsByID(int id){
		return adoptionRepository.findById(id);
	}

	@Transactional
	public void createAdoption(Adoption adoption) {
		adoptionRepository.save(adoption);
	}

	@Transactional(readOnly = true)
	public Collection<Adoption> findAllAdoptions(){
		return adoptionRepository.findAll();
	}


	@Transactional(readOnly = true)
	public Collection<Adoption> findAllDiferentAdoptionsAppliable(){
		List<Adoption>all= (List<Adoption>) adoptionRepository.findAll();
		List<Adoption> different = new ArrayList<>();
		if (all.isEmpty( )) return different;
		else {
		different.add(all.get(0));
		
		for(int i = 0; i<all.size();i++) {
			for(int k = 0; i<all.size();k++) {
				if (different.isEmpty()&& different.get(k).getPet().equals(all.get(i).getPet())) break;
				else different.add(all.get(i));
			}	
		}
		return different;
	}
	}		

	@Transactional(readOnly = true)
	public Collection<Adoption> findAdoptionsByPet(Integer id){
		return adoptionRepository.findPetById(id);
	}
	@Transactional
	public void aproveAdoption(Adoption adoption){
		adoption.getPet().setOwner(adoption.getNewOwner());
		
	}
	@Transactional
	public void deleteAdoptionByPet(int id) {
		List <Adoption> adoptionsDelete= (List<Adoption>) findAdoptionsByPet(id);
		for (int i = 0; i<adoptionsDelete.size();i++) {
			adoptionRepository.delete(adoptionsDelete.get(i));
		}
	}
	public Collection<Adoption> findAllAdoptionsAvaileble() {
		return adoptionRepository.findAll();
		
	}
}
