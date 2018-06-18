package com.shri.ad.de.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shri.ad.de.domain.IncomingRequestEntity;

@Repository
public interface IncomingRequestRepository extends JpaRepository<IncomingRequestEntity,Long> {
	
}
