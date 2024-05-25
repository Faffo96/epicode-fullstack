package com.company.Company.Repository;

import com.company.Company.Entity.Pc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PcRepository extends JpaRepository<Pc, Integer>  {
}
