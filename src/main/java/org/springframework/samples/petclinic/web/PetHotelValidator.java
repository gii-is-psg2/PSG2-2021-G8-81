package org.springframework.samples.petclinic.web;

import java.time.LocalDate;

import org.springframework.samples.petclinic.model.PetHotel;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PetHotelValidator implements Validator{

	
	public void validate(Object obj, Errors errors) {
		
		PetHotel petHotel = (PetHotel) obj;
		LocalDate dateEntry = petHotel.getDateEntry();
		LocalDate dateExit = petHotel.getDateExit();
		String data = petHotel.getData();
		if (dateEntry==null) {
			errors.rejectValue("dateEntry", " No puede dejar el campo vacio",
					"No puede dejar el campo vacio");
		}
		else if (dateEntry.isBefore(LocalDate.now())) {
			errors.rejectValue("dateEntry", " debe ser posterior a hoy",
					" debe ser posterior a hoy");}
		
		if (dateExit==null) {
			errors.rejectValue("dateExit", " No puede dejar el campo vacio",
					"No puede dejar el campo vacio");
			}
		else if (dateExit.isBefore(dateEntry)) {
			errors.rejectValue("dateExit", " debe ser posterior a la fecha de entrada",
					" debe ser posterior a la fecha de entrada");}
		if (data==null) {
			errors.rejectValue("data", " No puede dejar el campo vacio",
					"No puede dejar el campo vacio");
			}
		
		
	}

	
	public boolean supports(Class<?> clazz) {
		return PetHotel.class.isAssignableFrom(clazz);
	}
}
