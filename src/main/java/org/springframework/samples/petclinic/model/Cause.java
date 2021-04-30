package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "causes")
public class Cause extends BaseEntity{
	
	@NotBlank
	@Column(name = "name")
	private String name;
	
	@NotBlank
	@Column(name = "description")
	private String description;
	
	@Column(name = "budget")
	private Integer budget;
	
	@Column(name = "total_budget")
	private Integer totalBudget;
	
	@Column(name = "organization")
	@NotEmpty
	private String organization;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getBudget() {
		return budget;
	}

	public void setBudget(Integer budget) {
		this.budget = budget;
	}
	
	public Integer getTotalBudget() {
		return totalBudget;
	}

	public void setTotalBudget(Integer totalBudget) {
		this.totalBudget = totalBudget;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	@Override
	public String toString() {
		return "Cause [id=" + id + ", name=" + name + ", description=" + description + ", budget=" + budget
				+ ", totalBudget=" + totalBudget + ", organization=" + organization + "]";
	}
	
	

	

}
