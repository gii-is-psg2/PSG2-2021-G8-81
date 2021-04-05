
package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "pet_hotel")
public class PetHotel extends BaseEntity{


@Column(name = "date_entry")        
@DateTimeFormat(pattern = "dd/MM/yyyy")
private LocalDate dateEntry;


@Column(name = "date_exit")        
@DateTimeFormat(pattern = "dd/MM/yyyy")
private LocalDate dateExit;


@Column(name = "data")
private String data;

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

public String getData() {
	return data;
}

public void setData(String data) {
	this.data = data;
}

}
