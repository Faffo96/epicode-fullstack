package com.bookingManagement.bookingManagement.Repository;

import com.bookingManagement.bookingManagement.Entity.Location.Building;
import com.bookingManagement.bookingManagement.Entity.Location.Office;
import com.bookingManagement.bookingManagement.Enum.OfficeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OfficeRepository extends JpaRepository<Office, Integer> {
    @Query("SELECT o FROM Office o WHERE o.officeType = :officeType AND LOWER(o.building.city) LIKE LOWER(CONCAT('%', :city, '%'))")
    List<Office> findOfficesByTypeAndCity(@Param("officeType") OfficeType officeType, @Param("city") String city);


    List<Office> findByOfficeType(OfficeType officeType);

    @Query("SELECT o FROM Office o WHERE LOWER(o.building.city) LIKE LOWER(CONCAT('%', :city, '%'))")
    List<Office> findByBuilding_CityIgnoreCase(@Param("city") String city);
}
