package com.shri.ad.de.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shri.ad.de.domain.HourlyStatsEntity;
import com.shri.ad.de.repository.CustomerRepository;
import com.shri.ad.de.repository.HourlyStatsRepository;
import com.shri.ad.de.util.Constants;
import com.shri.ad.de.vo.CollectorServiceConfiguration;
import com.shri.ad.de.vo.HourlyStatsVO;

@Service("hourlyStatsService")
public class HourlyStatsServiceImpl implements HourlyStatsService {
	
	@Autowired
	HourlyStatsRepository hourlyStatsRepository;
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public void logRequestStatsByCustomerAndStatus(CollectorServiceConfiguration config,Long customerId,String status) {
	Calendar calendar = GregorianCalendar.getInstance();
	calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR),0);
	calendar.set(Calendar.SECOND,0);
	calendar.set(Calendar.MILLISECOND, 0);
	Timestamp currentHourTimestamp = new Timestamp(calendar.getTimeInMillis());
	
	Optional<HourlyStatsEntity> hse	= hourlyStatsRepository.findByCustomerIdAndTime(customerId, currentHourTimestamp);
	if(hse.isPresent()){
		HourlyStatsEntity entity = hse.get();
		if(Constants.STATUS_INVALID.equalsIgnoreCase(status)) {
			entity.setInvalidCount(entity.getInvalidCount()+1);
		}
		else{
			entity.setRequestCount(entity.getRequestCount()+1);
		}
		hourlyStatsRepository.save(entity);
		
	}else{
		HourlyStatsEntity entity = new HourlyStatsEntity();
		entity.setCustomer(customerRepository.findOne(customerId));
		if(Constants.STATUS_INVALID.equalsIgnoreCase(status)) {
			entity.setInvalidCount(1L);
			entity.setRequestCount(0L);
		}else{
			entity.setInvalidCount(0L);
			entity.setRequestCount(1L);
		}
		entity.setTime(currentHourTimestamp);
		hourlyStatsRepository.save(entity);
	}
	}

	@Override
	public List<HourlyStatsVO> generateStatsByCustomerAndStatus(Long customerId,String requestDate) {
		if(Constants.ALL.equalsIgnoreCase(requestDate)){
			return hourlyStatsRepository.findByCustomerId(customerId);
		}else{
			return hourlyStatsRepository.findByCustomerIdAndRequestDate(customerId, requestDate);
		}
		
	}
	
	

}
