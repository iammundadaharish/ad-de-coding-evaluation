package com.shri.ad.de.service;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shri.ad.de.domain.CustomerEntity;
import com.shri.ad.de.domain.IPBlacklistEntity;
import com.shri.ad.de.domain.IncomingRequestEntity;
import com.shri.ad.de.exception.CustomerInActiveException;
import com.shri.ad.de.exception.CustomerNotFoundException;
import com.shri.ad.de.exception.IPBlacklistedException;
import com.shri.ad.de.exception.UnprocessableEntityException;
import com.shri.ad.de.exception.UserAgentBlacklistedException;
import com.shri.ad.de.repository.CustomerRepository;
import com.shri.ad.de.repository.IPBlacklistRepository;
import com.shri.ad.de.repository.IncomingRequestRepository;
import com.shri.ad.de.repository.UserAgentRepository;
import com.shri.ad.de.util.Constants;
import com.shri.ad.de.vo.CollectorServiceConfiguration;

@Service
public class CollectorServiceImpl implements CollectorService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	IPBlacklistRepository ipBlacklistRepository;
	
	@Autowired
	IncomingRequestRepository incomingRequestRepository;
	
	@Autowired
	UserAgentRepository uar;
	
	@Autowired
	HourlyStatsService hourlyStatsService;


	@Override
	public IncomingRequestEntity validateAndSaveIncomingRequest(String userAgent,Long customerId,CollectorServiceConfiguration config) {
		if(uar.findByUA(userAgent) != null){
			hourlyStatsService.logRequestStatsByCustomerAndStatus(config,customerId,Constants.STATUS_INVALID);   
			throw new UserAgentBlacklistedException(Constants.STATUS_MSG_USER_AGENT_BLACKLISTED);
	       }
		Optional<CustomerEntity> crEntity = customerRepository.findById(customerId);
		if(!crEntity.isPresent()) {
    		throw new CustomerNotFoundException(Constants.STATUS_MSG_CUSTOMER_NOT_FOUND,config);
    	}else if(!crEntity.get().isActive()){
    		hourlyStatsService.logRequestStatsByCustomerAndStatus(config,customerId,Constants.STATUS_INVALID);
    		throw new CustomerInActiveException(Constants.STATUS_MSG_INACTIVE_CUSTOMER,config);
    	}
		
		Optional<IPBlacklistEntity> ipEntity = ipBlacklistRepository.findByIp(config.getRemoteIP().replaceAll("\\.", ""));
		if(ipEntity.isPresent()) {
			hourlyStatsService.logRequestStatsByCustomerAndStatus(config,customerId,Constants.STATUS_INVALID);
			throw new IPBlacklistedException(Constants.STATUS_MSG_IP_ADDRESS_BLACKLISTED,config);
		}

		IncomingRequestEntity ire;
		try {
			ire = new IncomingRequestEntity();
			ire.setCustomer(crEntity.get());
			ire.setRemoteIp(config.getRemoteIP());
			ire.setTimestamp(new Timestamp(config.getTimestamp()));
			ire.setUserId(config.getUserID());
			ire.setTagId(config.getTagID());
			ire = incomingRequestRepository.save(ire);
			hourlyStatsService.logRequestStatsByCustomerAndStatus(config,customerId,Constants.STATUS_VALID);
		} catch (Exception e) {
			hourlyStatsService.logRequestStatsByCustomerAndStatus(config,customerId,Constants.STATUS_INVALID);
			throw new UnprocessableEntityException(e.getMessage());		
		}
		
		return ire;
	}

}
