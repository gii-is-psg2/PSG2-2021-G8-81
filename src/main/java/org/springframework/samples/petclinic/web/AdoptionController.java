package org.springframework.samples.petclinic.web;


import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Adoption;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.service.AdoptionService;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AdoptionController {

	private final AdoptionService adoptionService;
	private PetService petService;
	private OwnerService ownerService;
	
	@Autowired
	public AdoptionController (AdoptionService adopService, PetService petServ, OwnerService ownerServ) {
		this.adoptionService=adopService;
		this.petService= petServ;
		this.ownerService=ownerServ;
	}

	@GetMapping(value = { "/adoptions" })
	public String showAdoptions(Map<String, Object> model) {
		Collection<Pet> pets = petService.findPestAdoptable();
		model.put("pets", pets);
		return "adoptions/adoptionList";
	}
	@GetMapping(value= {"/applyAdoption/{adoptId}"})
	public String applyAdoption(Map<String,Object> model,@PathVariable("adoptId") int id) {
		Pet pet=petService.findPetById(id);
		Adoption adp= new Adoption();
		adp.setPet(pet);
		adp.setPreviousOwner(pet.getOwner());
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String user = clienteDetails.getUsername();
	    Owner ow = ownerService.findOwnerByUsername(user);
		adp.setNewOwner(ow);
		model.put("adoption", adp);
		return "adoptions/applyAdoption";
	}
	@PostMapping(value = "/applyAdoption/{adoptId}")
	public String sendAppliedAdoption(@Valid Adoption adoption, Map<String,Object> model,BindingResult result,@PathVariable("adoptId") int id) {

		if(result.hasErrors()) {
			model.put("adoption", adoption);
			return "adoptions/newAdoption";
			
		}
		else {
			Pet pet = petService.findPetById(id);
			adoption.setPet(pet);
			Owner owner = pet.getOwner();
			adoption.setPreviousOwner(owner);
			UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		    String user = clienteDetails.getUsername();
		    Owner ow = ownerService.findOwnerByUsername(user);
		    adoption.setNewOwner(ow);

			this.adoptionService.createAdoption(adoption);
			return "redirect:/adoptions";
		}
		
	}
	@GetMapping(value = "/adop")
	public String initCreationForm(Map<String,Object> model) {
		Pet pet = new Pet();
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String user = clienteDetails.getUsername();
	    List<Pet> list = ownerService.findOwnerByUsername(user).getPets();
		model.put("pet", list);
		model.put("adoption", pet);
		return "adoptions/newAdoption";
		
	}
	@PostMapping(value = "/adop")
	public String proccesCreationForm(ModelMap model, @Valid Adoption adoption, BindingResult result) throws DataAccessException, DuplicatedPetNameException {
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String user = clienteDetails.getUsername();
	    List<Pet> list = ownerService.findOwnerByUsername(user).getPets();
		model.put("pet", list);
		if(result.hasErrors()) {
			model.put("adoption", adoption);
			return "adoptions/newAdoption";
			
		}else {
			Pet pet = petService.findPetById(adoption.getPet().getId());
			pet.setAdopt(true);
			this.petService.savePet(pet);
			}
		return "redirect:/adoptions";
		
	}

	
	
	@GetMapping(value = { "/adoptionAppliedToMyPet" })
	public String showAdoptionApliedToMyPet(Map<String, Object> model) {
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String user = clienteDetails.getUsername();
	    List<Pet> pets = ownerService.findOwnerByUsername(user).getPets();
		model.put("pets", pets);
		return "adoptions/petsToAdopt";
	}
	
	
	
	@GetMapping(value = { "/adoptionApplied/{petId}" })
	public String showAdoptionAplied(Map<String, Object> model,@PathVariable("petId") int id) {
		Collection<Adoption> adop = adoptionService.findAdoptionsByPet(id);
		model.put("adoptions", adop);
		return "adoptions/adoptionListMine";
	}
	
	
	
	
	@GetMapping(value = { "/adoption/{adoptionId}" })
	public String setAdoptionAplied(Map<String, Object> model,@PathVariable("adoptionId") int id) throws DataAccessException, DuplicatedPetNameException {
		Adoption adp = adoptionService.findAdoptionsByID(id);
		adp.setDescription("Usted ha sido seleccionado para la adopción");
		adp.setReviewed(true);
		adoptionService.aprobeAdoption(id);
		Pet pet= petService.findPetById(adoptionService.findAdoptionsByID(id).getPet().getId());
		pet.setOwner(adoptionService.findAdoptionsByID(id).getNewOwner());
		List<Adoption> removedAdoptions = (List<Adoption>) adoptionService.findAdoptionsByPet(adoptionService.findAdoptionsByID(id).getPet().getId());
		removedAdoptions.remove(adp);
		adoptionService.deleteAdoptionByPet(pet.getId());
		pet.setAdopt(false);
		petService.savePet(pet);
		return "redirect:/adoptions";
	}
}
