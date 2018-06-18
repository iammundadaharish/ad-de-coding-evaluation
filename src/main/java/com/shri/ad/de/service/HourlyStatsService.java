package com.shri.ad.de.service;

import java.util.List;

import com.shri.ad.de.vo.CollectorServiceConfiguration;
import com.shri.ad.de.vo.HourlyStatsVO;

public interface HourlyStatsService {
	
	void logRequestStatsByCustomerAndStatus(CollectorServiceConfiguration config,String status);
	
	List<HourlyStatsVO> generateStatsByCustomerAndStatus(Long customerId,String requestDate);

}
