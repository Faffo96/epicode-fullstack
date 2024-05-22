package com.bookingManagement.bookingManagement.Service;

import com.bookingManagement.bookingManagement.Entity.Location.Building;
import com.bookingManagement.bookingManagement.Entity.User.User;
import com.bookingManagement.bookingManagement.Repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;

    public void postBuilding(Building building){
        buildingRepository.save(building);
    }

    public Building getBuilding(int id){
        return buildingRepository.findById(id).get();
    }

    public List<Building> getBuildings(){
        return buildingRepository.findAll();
    }
}
