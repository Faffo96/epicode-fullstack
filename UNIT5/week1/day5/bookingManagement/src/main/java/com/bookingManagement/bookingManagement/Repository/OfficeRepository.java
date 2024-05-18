package com.bookingManagement.bookingManagement.Repository;

import com.bookingManagement.bookingManagement.Entity.Location.Building;
import com.bookingManagement.bookingManagement.Entity.Location.Office;
import com.bookingManagement.bookingManagement.Enum.OfficeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OfficeRepository extends JpaRepository<Office, Integer> {
    @Query("SELECT o FROM Office o WHERE o.officeType = :officeType AND o.building.city = :city")
    List<Office> findOfficesByTypeAndCity(@Param("officeType") OfficeType officeType, @Param("city") String city);
}
