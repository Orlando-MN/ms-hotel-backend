package com.hotelsbook.hotel.repository;

import com.hotelsbook.hotel.entity.HotelAvailable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<HotelAvailable, Long> {

    @Query(value = "CALL GetAvailableHotelsByCity(:startDate, :endDate, :cityId)", nativeQuery = true)
    List<HotelAvailable> findAvailableHotelsByCity(
        @Param("startDate") Date startDate,
        @Param("endDate") Date endDate,
        @Param("cityId") Integer cityId
    );
}
