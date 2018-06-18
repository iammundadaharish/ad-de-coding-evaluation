package com.shri.ad.de.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "\"ua_blacklist\"")
public class UserAgentEntity extends AbstractEntity {
	
	
	@Column(name= "ua")
	private String uA;

	public String getuA() {
		return uA;
	}

	public void setuA(String uA) {
		this.uA = uA;
	}

	
	

}
