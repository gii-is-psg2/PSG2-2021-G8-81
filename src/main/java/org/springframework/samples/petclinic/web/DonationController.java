package org.springframework.samples.petclinic.web;

import javax.validation.Valid;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.service.CauseService;
import org.springframework.samples.petclinic.service.DonationService;
import org.springframework.samples.petclinic.service.exceptions.TooMuchMoneyException;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

public class DonationController {
	
	private static final String VIEW_DONATION_CREATE_FORM = "donation/createDonation";

	private final CauseService causeService;
	private final DonationService donationService;

	
	
	public DonationController(CauseService causeService, DonationService donationService) {
		this.causeService = causeService;
		this.donationService = donationService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = {"/donation/new"})
	public String newDonation(ModelMap model) {		
		Donation donation = new Donation();
		model.put("donations", donation);
		return VIEW_DONATION_CREATE_FORM;

	}
	
	@PostMapping(value = {"/donation/new"})
	public String processCreationForm(@Valid Donation donation, BindingResult result) throws DataAccessException, TooMuchMoneyException {
	  	if (result.hasErrors()) {
			return VIEW_DONATION_CREATE_FORM;
		}
		
		else {
			try {				
				Cause cause = causeService.findCauseById(donation.getCause().getId());
				
			} catch (Exception e) {
				result.rejectValue("money", "No puede donar tanto dinero", "No puede donar tanto dinero");
				return VIEW_DONATION_CREATE_FORM;
			}
			donationService.save(donation);
			return "redirect:/causes";
		}
	}
	
	

}
