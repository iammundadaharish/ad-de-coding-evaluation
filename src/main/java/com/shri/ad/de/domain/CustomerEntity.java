package com.shri.ad.de.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "\"customer\"")
//@Where(clause = "active = 1") -- Commenting this out, so that request for disabled customers can also be tracked.
public class CustomerEntity extends AbstractEntity {
	
	
	private String name;
	private boolean active;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	

}
