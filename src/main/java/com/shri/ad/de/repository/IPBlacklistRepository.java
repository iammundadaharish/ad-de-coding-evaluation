package com.shri.ad.de.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shri.ad.de.domain.IPBlacklistEntity;

@Repository
public interface IPBlacklistRepository  extends JpaRepository<IPBlacklistEntity,Long> {
	Optional<IPBlacklistEntity> findByIp(String ip);
}
