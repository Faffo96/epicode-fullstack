package com.bookingManagement.bookingManagement.Repository;

import com.bookingManagement.bookingManagement.Entity.Location.Office;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeRepository extends JpaRepository<Office, Integer> {
}
