package org.springframework.samples.petclinic.web;

import java.util.Map;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public class DonationController {
	
	private static final String VIEW_DONATION_CREATE_FORM = "donation/newDonation";

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
	public String s(ModelMap model,@PathVariable("donationId") int id) {	
		Cause cause = causeService.findCauseById(id);
		Donation donation = new Donation();
		donation.setCause(cause);
		model.put("donation", donation);
		return VIEW_DONATION_CREATE_FORM;

	}
	

	@GetMapping(value = "/donation/{causeId}")
	public String newDonation(Map<String, Object> model,@PathVariable("causeId") int id) {
		Cause causes = causeService.findCauseById(id);
		Donation donation = new Donation();
		donation.setCause(causes);
		model.put("cause", causes);
		model.put("donation", donation);
		return "wellcome";
	}
	
	@PostMapping(value = {"/donation/{causeId}"})
	public String processCreationForm(@Valid Donation donation,@PathVariable("donationId") int id, BindingResult result) throws DataAccessException, TooMuchMoneyException {
	  	if (result.hasErrors()) {
			return VIEW_DONATION_CREATE_FORM;
		}
		
		else {
			try {
				
			} catch (Exception e) {
				Cause cause = causeService.findCauseById(id);
				result.rejectValue("money", "Solo puede donar hasta " + (cause.getBudget()-cause.getTotalBudget()), "Solo puede donar hasta " + (cause.getBudget()-cause.getTotalBudget()));
				return VIEW_DONATION_CREATE_FORM;
			}
			donationService.save(donation);
			return "redirect:/causes";
		}
	}
	
	

}
