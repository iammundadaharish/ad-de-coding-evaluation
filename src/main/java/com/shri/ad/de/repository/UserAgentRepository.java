package com.shri.ad.de.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shri.ad.de.domain.UserAgentEntity;

@Repository
public interface UserAgentRepository  extends JpaRepository<UserAgentEntity,Long> {
	UserAgentEntity findByUA(String ua);
}
