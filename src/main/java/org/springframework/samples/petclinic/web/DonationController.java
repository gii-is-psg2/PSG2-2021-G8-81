package org.springframework.samples.petclinic.web;


import java.util.Collection;

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
public class DonationController {
	
	private static final String VIEW_DONATION_CREATE_FORM = "donation/newDonation";

	private final DonationService donationService;
		private final OwnerService ownerService;
		private final CauseService causeService;
	@Autowired
	public DonationController( DonationService donationService,OwnerService ownerService,
			CauseService causeService) {
		this.donationService = donationService;
				this.ownerService = ownerService;
				this.causeService= causeService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = {"/donation"})
	public String initCreationForm(ModelMap model) {		
		Donation donation = new Donation();
		model.put("donation", donation);
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String user = clienteDetails.getUsername();
		Owner owner  = ownerService.findOwnerByUsername(user);
		donation.setOwner(owner);
		Collection<Cause> list= causeService.findCauses();
		model.put("cause", list);
		return VIEW_DONATION_CREATE_FORM;

	}
	
	
	@PostMapping(value = {"/donation"})
	public String processCreationForm(@Valid Donation donation, BindingResult result,ModelMap model) throws DataAccessException, TooMuchMoneyException {
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String user = clienteDetails.getUsername();
		Owner owner  = ownerService.findOwnerByUsername(user);
		donation.setOwner(owner);
		if (result.hasErrors()) {
			Collection<Cause> list= causeService.findCauses();
			model.put("cause", list);
			return VIEW_DONATION_CREATE_FORM;
		}
		
		else {
			try {		
				donation.setOwner(owner);
				donationService.save(donation);
				return "redirect:/causes";
			} catch (TooMuchMoneyException e) {
				Collection<Cause> list= causeService.findCauses();
				model.put("cause", list);
				result.rejectValue("money", "No puede donar tanto dinero", "No puede donar tanto dinero");
				return VIEW_DONATION_CREATE_FORM;
			}
			
		}
	}
	
	

}
