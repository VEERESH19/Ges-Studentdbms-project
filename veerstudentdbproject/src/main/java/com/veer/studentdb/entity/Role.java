package com.veer.studentdb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_id")
	private int role_id;

	@Column(name = "role_name")
	private String role_name;

	@Column(name = "role_description")
	private String role_description;

	public Role() {
		log.info("-- Role Entity");
	}

	
}
