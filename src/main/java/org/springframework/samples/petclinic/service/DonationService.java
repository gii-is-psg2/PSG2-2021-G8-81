package org.springframework.samples.petclinic.service;
import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.repository.DonationRepository;
import org.springframework.samples.petclinic.service.exceptions.TooMuchMoneyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DonationService {
	
	private DonationRepository donationRepository;
	private CauseService causeService;
	@Autowired
	public DonationService(DonationRepository donationRepository, CauseService causeService) {
		this.donationRepository= donationRepository;
		this.causeService= causeService;
	}
	@Transactional(readOnly= true)
	public Collection<Donation> findAllDonations(){
		return donationRepository.findAll();
	}
	
	@Transactional
	public void save(Donation donation)throws DataAccessException, TooMuchMoneyException{
		Cause cause = causeService.findCauseById(donation.getCause().getId());
		Integer money = donation.getMoney();
		if (money>cause.getBudget()-cause.getTotalBudget()) {
			throw new TooMuchMoneyException();
		}
		cause.setTotalBudget(money+cause.getTotalBudget());
		causeService.saveCause(cause);
		donation.setDate(LocalDateTime.now());
		donationRepository.save(donation);		
	}

	@Transactional(readOnly= true)
	public Collection<Donation> findAllDonationsByCause(Integer id){
		return donationRepository.findDonationsByCause(id);
	}

}
