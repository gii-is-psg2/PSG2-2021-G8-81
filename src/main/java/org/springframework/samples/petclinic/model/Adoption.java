package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "adoptions")
public class Adoption extends BaseEntity{

	@NotNull
	@ManyToOne
	@JoinColumn(name="pet_id")
	private Pet pet;
	
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="owner_id")
	private Owner previousOwner;

	@Column(name="description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name="new_owner_id")
	private Owner newOwner;
	
	
	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public Owner getPreviousOwner() {
		return previousOwner;
	}

	public void setPreviousOwner(Owner previousOwner) {
		this.previousOwner = previousOwner;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Owner getNewOwner() {
		return newOwner;
	}

	public void setNewOwner(Owner newOwner) {
		this.newOwner = newOwner;
	}
	
	
}
