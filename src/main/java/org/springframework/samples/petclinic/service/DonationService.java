package org.springframework.samples.petclinic.service;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.repository.DonationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DonationService {

	private DonationRepository donationRepository;
	
	@Autowired
	public DonationService(DonationRepository donationRepository) {
		this.donationRepository= donationRepository;
	}
	@Transactional(readOnly= true)
	public Collection<Donation> findAllDonations(){
		return donationRepository.findAll();
	}

	@Transactional(readOnly= true)
	public Collection<Donation> findAllDonationsByCause(Integer id){
		return donationRepository.findDonationsByCause(id);
	}

}
