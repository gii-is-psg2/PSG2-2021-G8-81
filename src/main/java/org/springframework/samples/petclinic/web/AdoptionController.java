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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
		Collection<Adoption> adop = adoptionService.findAllAdoptions();
		model.put("adoptions", adop);
		return "adoptions/adoptionList";
	}
	
	@GetMapping(value = "/adop")
	public String initCreationForm(Map<String,Object> model) {
		Adoption adoption = new Adoption();
		Owner owner = new Owner();
		Pet pet = new Pet();
	    adoption.setPet(pet);
	    adoption.setPreviousOwner(owner);
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String user = clienteDetails.getUsername();
	    List<Pet> list = ownerService.findOwnerByUsername(user).getPets();
		model.put("pet", list);
		model.put("adoption", adoption);
		return "adoptions/newAdoption";
		
	}
	@PostMapping(value = "/adop")
	public String proccesCreationForm(ModelMap model, @Valid Adoption adoption, BindingResult result) throws DataAccessException {
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String user = clienteDetails.getUsername();
	    List<Pet> list = ownerService.findOwnerByUsername(user).getPets();
		model.put("pet", list);
		if(result.hasErrors()) {
			model.put("adoption", adoption);
			return "adoptions/newAdoption";
			
		}else {
			Pet pet = petService.findPetById(adoption.getPet().getId());
			adoption.setPet(pet);
			Owner owner = ownerService.findOwnerById(adoption.getPreviousOwner().getId());
			adoption.setPreviousOwner(owner);
			this.adoptionService.createAdoption(adoption);
		}
		return "redirect:/adoptions";
		
	}
}
