package com.company.Company.Repository;

import com.company.Company.Entity.Smartphone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmartphoneRepository extends JpaRepository<Smartphone, Integer>  {
}
