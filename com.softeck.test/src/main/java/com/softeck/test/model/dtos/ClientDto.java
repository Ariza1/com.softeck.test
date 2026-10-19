package com.softeck.test.model.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDto {
	Long id;
	String firstName;
	String lastName;
	String surname;
	String phone;
	

	 public ClientDto(Long id, String firstName, String lastName, String surname, String phone){
		super();
		this.id = id; 
		this.firstName = firstName; 
		this.lastName = lastName; 
		this.surname = surname; 
		this.phone = phone;
	 }
}
