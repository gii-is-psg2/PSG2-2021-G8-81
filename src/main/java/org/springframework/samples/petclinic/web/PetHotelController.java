package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetHotel;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.PetHotelService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.exceptions.TwoPetsBookingException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PetHotelController {
	
private final PetHotelService petHotelService;
private final PetService petService;
private final OwnerService ownerService;

@Autowired
public PetHotelController (PetHotelService petHotelService,PetService petService,OwnerService ownerService) {
	this.petHotelService=petHotelService;
	this.petService = petService;
	this.ownerService = ownerService;

}

@InitBinder("petHotel")
public void initPetBinder(WebDataBinder dataBinder) {
	dataBinder.setValidator(new PetHotelValidator());
	}

@GetMapping(value={"/petsHotel"})
public String findAllCitas(Map<String,Object> model) {
	Collection<PetHotel> petsInHotel=petHotelService.findAllPetHotel();
	PetHotel petHotel = new PetHotel();
	Pet pet = new Pet();
	petHotel.setPet(pet);
	model.put("petHotel", petHotel);
	model.put("petsHotel", petsInHotel);
	return "petHotel/petsInHotelListed";
}

@GetMapping(value ="/petHotel/new")
public String initCreationForm(ModelMap model) {
	PetHotel petHotel = new PetHotel();
	Pet pet = new Pet();
	petHotel.setPet(pet);
	
	UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	String user = clienteDetails.getUsername();
	List<Pet> list = ownerService.findOwnerByUsername(user).getPets();
	model.put("pet", list);
	model.put("petHotel", petHotel);
	return "petHotel/booking";
}

@PostMapping(value = "/petHotel/new")
public String processCreationForm(@Valid PetHotel petHotel, BindingResult result,
		ModelMap modelMap) throws DataAccessException{
	UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	String user = clienteDetails.getUsername();
	List<Pet> list = ownerService.findOwnerByUsername(user).getPets();
	modelMap.put("pet", list);
	Pet pet = petService.findPetById(petHotel.getPet().getId());
	if(result.hasErrors()) {
		modelMap.put("petHotel", petHotel);
		return "petHotel/booking";
	}
	else {
		try {
			petHotel.setPet(pet);
			this.petHotelService.saveCita(petHotel);
			return "redirect:/petsHotel";
		}
	catch(TwoPetsBookingException ex) {
			
		 result.rejectValue("dateEntry", "por favor seleccione una estancia para la mascota posterior a " + petHotel.getDateExit(), "por favor seleccione una estancia para la mascota posterior a " + petHotel.getDateExit());

		 return "petHotel/booking";
	}
	}
	
}
}
