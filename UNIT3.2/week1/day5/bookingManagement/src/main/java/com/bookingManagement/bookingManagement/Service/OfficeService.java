package com.bookingManagement.bookingManagement.Service;

import com.bookingManagement.bookingManagement.Entity.Location.Office;
import com.bookingManagement.bookingManagement.Entity.User.User;
import com.bookingManagement.bookingManagement.Enum.OfficeType;
import com.bookingManagement.bookingManagement.Exception.BookingManagementException;
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

    public List<Office> findOfficesByTypeAndCity(OfficeType officeType, String city) throws BookingManagementException{
        List<Office> offices = officeRepository.findOfficesByTypeAndCity(officeType, city);
            if (findOfficesByCity(city).isEmpty()) {
                throw new BookingManagementException("Nessun ufficio trovato nella città '" + city + "'");
            } else if (!findOfficesByCity(city).isEmpty() && offices.isEmpty()) {
                throw new BookingManagementException("Nessun ufficio trovato con il tipo '" + officeType + "'");
            }
        return offices;
    }

    public List<Office> findOfficesByType(OfficeType officeType) {
        return officeRepository.findByOfficeType(officeType);
    }

    public List<Office> findOfficesByCity(String city) {
        return officeRepository.findByBuilding_CityIgnoreCase(city);
    }
}
