package com.bookingManagement.bookingManagement.Service;

import com.bookingManagement.bookingManagement.Entity.Location.Office;
import com.bookingManagement.bookingManagement.Entity.User.User;
import com.bookingManagement.bookingManagement.Repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeService {
    @Autowired
    private OfficeRepository officeRepository;

    public void postOffice(Office office){
        officeRepository.save(office);
    }

    public Office getOffice(int id){
        return officeRepository.findById(id).get();
    }

    public List<Office> getOffices(){
        return officeRepository.findAll();
    }
}
