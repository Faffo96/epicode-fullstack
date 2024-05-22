package com.exercise.exercise.service;

import com.exercise.exercise.bean.DiningTable;
import com.exercise.exercise.bean.Order;
import com.exercise.exercise.repository.DiningTableRepository;
import com.exercise.exercise.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiningTableService {
    @Autowired
    private DiningTableRepository diningRepository;

    public void addProduct(DiningTable diningTable){
        diningRepository.save(diningTable);
    }

    public DiningTable getDiningTable(int id){
        return diningRepository.findById(id).get();
    }

    public List<DiningTable> getDiningTables(){
        return diningRepository.findAll();
    }
}
