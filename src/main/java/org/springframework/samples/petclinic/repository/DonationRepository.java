package org.springframework.samples.petclinic.repository;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Donation;

public interface DonationRepository extends CrudRepository<Donation, Integer>{

@Query("SELECT donation FROM Donation donation WHERE donation.date=: date")
List<Donation> findDonationsByDate (@Param("date") LocalDateTime date);

Collection<Donation> findAll()throws DataAccessException;

@Query("SELECT donation FROM Donation donation WHERE donation.cause.id=: id")
List<Donation> findDonationsByCause (@Param("id") Integer causeId);

}
