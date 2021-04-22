package org.springframework.samples.petclinic.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "donation")
public class Donation extends NamedEntity{

@Column(name = "money")
private Integer money;

@Column(name="date")
private LocalDateTime date;

@ManyToOne
@JoinColumn(name= "owner_id")
private Owner owner;

@ManyToOne
@JoinColumn(name= "cause")
private Cause cause;

public Cause getCause() {
	return cause;
}

public void setCause(Cause cause) {
	this.cause = cause;
}

public Integer getMoney() {
	return money;
}

public void setMoney(Integer money) {
	this.money = money;
}

public LocalDateTime getDate() {
	return date;
}

public void setDate(LocalDateTime date) {
	this.date = date;
}

public Owner getOwner() {
	return owner;
}

public void setOwner(Owner owner) {
	this.owner = owner;
}

}
