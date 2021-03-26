package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.PetHotel;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.PetHotelService;
import org.springframework.samples.petclinic.service.PetService;
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


@Autowired
public PetHotelController (PetHotelService petHotelService,PetService petService,OwnerService ownerService) {
	this.petHotelService=petHotelService;

}
@InitBinder("petHotel")
public void initPetBinder(WebDataBinder dataBinder) {
	dataBinder.setValidator(new PetHotelValidator());
}

@GetMapping(value={"/petsHotel"})
public String findAllCitas(Map<String,Object> model) {
	Collection<PetHotel> petsInHotel=petHotelService.findAllPetHotel();
	PetHotel petHotel = new PetHotel();
	model.put("petHotel", petHotel);
	model.put("petsHotel", petsInHotel);
	return "petHotel/petsInHotelListed";
}
@GetMapping(value ="/petHotel/new")
public String initCreationForm(ModelMap model) {
	PetHotel petHotel = new PetHotel();
	model.put("petHotel", petHotel);
	return "petHotel/booking";
}

@PostMapping(value = "/petHotel/new")
public String processCreationForm(@Valid PetHotel petHotel, BindingResult result, ModelMap modelMap) throws DataAccessException{
	if(result.hasErrors()) {
		modelMap.put("petHotel", petHotel);
		return "redirect:/petHotel/new";
	}

	petHotelService.savePetHotel(petHotel);
	return "redirect:/petsHotel";
}
}
