package com.shri.ad.de.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "\"ip_blacklist\"")
public class IPBlacklistEntity extends AbstractEntity {
	
	
	@Column(name= "ip")
	private String ip;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
