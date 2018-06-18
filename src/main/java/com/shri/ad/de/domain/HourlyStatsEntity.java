package com.shri.ad.de.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "\"hourly_stats\"")
public class HourlyStatsEntity extends AbstractEntity{
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
	private CustomerEntity customer;
	@Column(name="time")
	private Timestamp time;
	@Column(name="request_count")
	private Long requestCount;
	@Column(name="invalid_count")
	private Long invalidCount;
	public CustomerEntity getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public Long getRequestCount() {
		return requestCount;
	}
	public void setRequestCount(Long requestCount) {
		this.requestCount = requestCount;
	}
	public Long getInvalidCount() {
		return invalidCount;
	}
	public void setInvalidCount(Long invalidCount) {
		this.invalidCount = invalidCount;
	}
	
	
	

}
