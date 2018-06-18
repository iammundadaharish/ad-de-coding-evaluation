package com.shri.ad.de.vo;

public class HourlyStatsVO {
	
	String customerName;
	Long numberOfValidRequest;
	Long numberOfInValidRequest;
	Long totalNumberOfRequest;
	String time;
	
	public HourlyStatsVO() {
		
	}
	
	public HourlyStatsVO(String customerName,Long numberOfValidRequest, Long numberOfInValidRequest, String time) {
		this.customerName = customerName;
		this.numberOfInValidRequest = numberOfInValidRequest;
		this.numberOfValidRequest = numberOfValidRequest;
		this.totalNumberOfRequest = numberOfValidRequest+numberOfInValidRequest;
		this.time = time;
	}
	
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Long getNumberOfValidRequest() {
		return numberOfValidRequest;
	}
	public void setNumberOfValidRequest(Long numberOfValidRequest) {
		this.numberOfValidRequest = numberOfValidRequest;
	}
	public Long getNumberOfInValidRequest() {
		return numberOfInValidRequest;
	}
	public void setNumberOfInValidRequest(Long numberOfInValidRequest) {
		this.numberOfInValidRequest = numberOfInValidRequest;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	public Long getTotalNumberOfRequest() {
		return totalNumberOfRequest;
	}

	public void setTotalNumberOfRequest(Long totalNumberOfRequest) {
		this.totalNumberOfRequest = totalNumberOfRequest;
	}
	
	
	

}
