package com.exercise.exercise.repository;

import com.exercise.exercise.bean.DiningTable;
import com.exercise.exercise.bean.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiningTableRepository extends JpaRepository<DiningTable, Integer> {
}
