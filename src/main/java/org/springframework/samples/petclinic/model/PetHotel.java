
package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "pet_hotel")
public class PetHotel extends BaseEntity{

@NotNull
@Column(name = "dateEntry")        
@DateTimeFormat(pattern = "dd/MM/yyyy")
private LocalDate dateEntry;

@NotNull
@Column(name = "dateExit")        
@DateTimeFormat(pattern = "dd/MM/yyyy")
private LocalDate dateExit;

@NotNull
@ManyToOne
@JoinColumn(name = "pet_id")
private Pet pet;


@Column(name = "description")
private String description;

public PetHotel() {
	this.dateEntry = LocalDate.now();
	this.dateExit =dateEntry.plusDays(1);
}
public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public LocalDate getDateEntry() {
	return dateEntry;
}

public void setDateEntry(LocalDate dateEntry) {
	this.dateEntry = dateEntry;
}

public LocalDate getDateExit() {
	return dateExit;
}

public void setDateExit(LocalDate dateExit) {
	this.dateExit = dateExit;
}

public Pet getPet() {
	return pet;
}

public void setPet(Pet pet) {
	this.pet = pet;
}

}
