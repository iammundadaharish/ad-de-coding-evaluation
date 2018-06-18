package com.shri.ad.de.service;

import com.shri.ad.de.domain.IncomingRequestEntity;
import com.shri.ad.de.vo.CollectorServiceConfiguration;

public interface CollectorService {

	IncomingRequestEntity validateAndSaveIncomingRequest(String userAgent,Long customerId,
			CollectorServiceConfiguration incomingRequest);

}
