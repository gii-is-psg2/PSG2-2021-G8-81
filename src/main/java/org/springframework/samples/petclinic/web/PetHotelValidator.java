package org.springframework.samples.petclinic.web;

import java.time.LocalDate;

import org.springframework.samples.petclinic.model.PetHotel;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PetHotelValidator implements Validator{

	
	private static final String REQUIRED = "requerido";

	@Override
	public void validate(Object obj, Errors errors) {

		
		PetHotel petHotel = (PetHotel) obj;
		LocalDate dateEntry = petHotel.getDateEntry();
		if (dateEntry==null) {
			errors.rejectValue("dateEntry", " No puede dejar el campo vacio",
					"No puede dejar el campo vacio");

		}
		else if (petHotel.getDateEntry().isBefore(LocalDate.now())) {
			errors.rejectValue("dateEntry", " debe ser posterior a hoy",
					" debe ser posterior a hoy");}
		else if (petHotel.getDateExit()== null) {
			errors.rejectValue("dateExit", REQUIRED, REQUIRED);
			}
		if (petHotel.getDateEntry()!=null && petHotel.getDateExit()!=null && petHotel.getDateExit().isBefore(petHotel.getDateEntry())) {
			errors.rejectValue("dateExit", " debe ser posterior a la fecha de entrada",
					" debe ser posterior a la fecha de entrada");}
		if (petHotel.getDateExit()== null) {
			errors.rejectValue("dateExit", REQUIRED, REQUIRED);
		}
		
	}
	public boolean supports(Class<?> clazz) {
		return PetHotel.class.isAssignableFrom(clazz);
	}
}
