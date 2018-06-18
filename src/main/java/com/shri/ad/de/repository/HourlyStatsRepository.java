package com.shri.ad.de.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shri.ad.de.domain.HourlyStatsEntity;
import com.shri.ad.de.vo.HourlyStatsVO;

@Repository
public interface HourlyStatsRepository  extends JpaRepository<HourlyStatsEntity,Long> {
	
	Optional<HourlyStatsEntity> findByCustomerIdAndTime(Long customerId, Timestamp time);
    
	@Query("SELECT distinct new com.shri.ad.de.vo.HourlyStatsVO(hse.customer.name, sum(hse.requestCount) , sum(hse.invalidCount) ,  date_format(hse.time,'%Y-%m-%d')) FROM HourlyStatsEntity hse where hse.customer.id  = :customerId and date_format(hse.time,'%Y-%m-%d') = :requestDate group by hse.customer.id,date_format(hse.time,'%Y-%m-%d') ")
	List<HourlyStatsVO> findByCustomerIdAndRequestDate(@Param("customerId") Long customerId, @Param("requestDate") String requestDate);

	@Query("SELECT distinct new com.shri.ad.de.vo.HourlyStatsVO(hse.customer.name, sum(hse.requestCount) , sum(hse.invalidCount) ,  date_format(hse.time,'%Y-%m-%d')) FROM HourlyStatsEntity hse where hse.customer.id  = :customerId  group by hse.customer.id,date(hse.time) ")
	List<HourlyStatsVO> findByCustomerId(@Param("customerId") Long customerId);
	
}
