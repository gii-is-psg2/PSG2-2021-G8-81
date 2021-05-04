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
	@Autowired
	private CauseService causeService;
	
	@Autowired
	public DonationService(DonationRepository donationRepository) {
		this.donationRepository= donationRepository;
	}
	@Transactional(readOnly= true)
	public Collection<Donation> findAllDonations(){
		return donationRepository.findAll();
	}
	
	@Transactional
	public void save(Donation donation)throws DataAccessException, TooMuchMoneyException{
		Cause cause = this.causeService.findCauseById(donation.getCause().getId());
		if (cause.getBudget()-cause.getTotalBudget()<donation.getMoney()) {
			throw new TooMuchMoneyException();
		}
		cause.setTotalBudget(cause.getTotalBudget()+donation.getMoney());
		donation.setDate(LocalDateTime.now());
		donationRepository.save(donation);		
	}

	@Transactional(readOnly= true)
	public Collection<Donation> findAllDonationsByCauseId(Integer id){
		return donationRepository.findDonationsByCause(id);
	}
	@Transactional(readOnly= true)
	public Collection<Donation> findAllDonationsByCause(Cause cause){
		return donationRepository.findDonationsByCause(cause.getId());
	}

}
