package org.springframework.samples.petclinic.web;

import java.util.Map;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.CauseService;
import org.springframework.samples.petclinic.service.DonationService;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.exceptions.TooMuchMoneyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class DonationController {
	
	private static final String VIEW_DONATION_CREATE_FORM = "donation/newDonation";


	
	private final DonationService donationService;
	private final CauseService causeService;
	
	@Autowired
	public DonationController(CauseService causeService, DonationService donationService) {
		this.donationService = donationService;
		this.causeService = causeService;
	}

	@InitBinder("donation")
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	


	@GetMapping(value = "/donation/{causeId}")
	public String newDonation(Map<String, Object> model,@PathVariable("causeId") int id) {
		Cause causes = causeService.findCauseById(id);
		Donation donation = new Donation();
		donation.setCause(causes);
		model.put("donation", donation);
		return VIEW_DONATION_CREATE_FORM;
	}
	
	@PostMapping(value = {"/donation/{causeId}"})
	public String processCreationForm(@Valid Donation donation,@PathVariable("causeId") int id, BindingResult result,ModelMap modelMap) throws DataAccessException, TooMuchMoneyException {
		Cause causes = causeService.findCauseById(id);
		donation.setCause(causes);
		if (result.hasErrors()) {
	  		modelMap.put("donation", donation);

			return VIEW_DONATION_CREATE_FORM;
		}
		
		else {

			try {
				this.donationService.save(donation);
			} catch (TooMuchMoneyException e) {
				
				result.rejectValue("money", "Solo puede donar hasta "+(causes.getBudget()-causes.getTotalBudget()) , "Solo puede donar hasta "+(causes.getBudget()-causes.getTotalBudget()));
				return VIEW_DONATION_CREATE_FORM;
			}
			
			return "redirect:/causes";
		}
	
	}
	
}
