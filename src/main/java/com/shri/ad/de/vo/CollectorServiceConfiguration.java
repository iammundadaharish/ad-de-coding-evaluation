package com.shri.ad.de.vo;
import com.shri.ad.de.util.BaseServiceConfiguration;

public class CollectorServiceConfiguration  extends BaseServiceConfiguration{
	
	private Integer tagID;
	private String userID;
	private String remoteIP;
	private Long timestamp;
	
	public Integer getTagID() {
		return tagID;
	}
	public void setTagID(Integer tagID) {
		this.tagID = tagID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getRemoteIP() {
		return remoteIP;
	}
	public void setRemoteIP(String remoteIP) {
		this.remoteIP = remoteIP;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
	

	
}
