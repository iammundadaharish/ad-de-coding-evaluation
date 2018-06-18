package com.shri.ad.de.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shri.ad.de.domain.IncomingRequestEntity;
import com.shri.ad.de.exception.MalformedJsonException;
import com.shri.ad.de.service.CollectorService;
import com.shri.ad.de.service.HourlyStatsService;
import com.shri.ad.de.vo.CollectorServiceConfiguration;
import com.shri.ad.de.vo.HourlyStatsVO;

@RestController
@RequestMapping(value = "api/v1/")
public class CollectorServiceController {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	CollectorService collectorService;
	
	@Autowired
	HourlyStatsService hourlyStatsService;

	/**
	 * Accept API
	 *
	 * @param incomingRequest valid json 
	 * @return IncomingRequestEntity stored
	 ***/
	@RequestMapping(value = "collect", method = RequestMethod.POST)
	public ResponseEntity<IncomingRequestEntity> acceptRequest(@RequestHeader(value = "User-Agent") String userAgent,
			@Valid @RequestBody String incomingRequest) {
		CollectorServiceConfiguration configuration = null;
		IncomingRequestEntity ire = null;
		try {
			configuration = objectMapper.readValue(incomingRequest,
					CollectorServiceConfiguration.class);
			ire = collectorService.validateAndSaveIncomingRequest(userAgent, configuration);

		} catch (JsonParseException e) {
			hourlyStatsService.logRequestStatsByCustomerAndStatus(configuration,"INVALID");
			throw new MalformedJsonException("Input request json is malformed");
		} catch (JsonMappingException e) {
			hourlyStatsService.logRequestStatsByCustomerAndStatus(configuration,"INVALID");
			throw new MalformedJsonException("Input request json is malformed");
		} catch (IOException e) {
			hourlyStatsService.logRequestStatsByCustomerAndStatus(configuration,"INVALID");
			e.printStackTrace();
		}

		return ResponseEntity.ok(ire);
	}
	
	/**
	 * Accept API
	 *
	 * @param customerId -- customer id of the customer for which report is to be generated
	 * @param requestDate -- ALL for all Days for the customer.
	 * @return service stats
	 ***/
	@RequestMapping(value = "customer/{customerId}/requestDate/{requestDate}/stats", method = RequestMethod.GET)
	public ResponseEntity<List<HourlyStatsVO>> generateStats(@PathVariable Long customerId,@PathVariable String requestDate){
		List<HourlyStatsVO> stats = hourlyStatsService.generateStatsByCustomerAndStatus(customerId,requestDate);
		return ResponseEntity.ok(stats);
	}

}
