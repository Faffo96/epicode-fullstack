package com.bookingManagement.bookingManagement.Repository;

import com.bookingManagement.bookingManagement.Entity.Location.Building;
import com.bookingManagement.bookingManagement.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserId(int userId);
}
