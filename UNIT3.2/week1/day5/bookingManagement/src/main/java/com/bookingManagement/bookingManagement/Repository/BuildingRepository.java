package com.bookingManagement.bookingManagement.Repository;

import com.bookingManagement.bookingManagement.Entity.Location.Building;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building, Integer> {
}
