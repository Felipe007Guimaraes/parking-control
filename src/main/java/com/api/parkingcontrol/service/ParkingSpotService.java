package com.api.parkingcontrol.service;

import com.api.parkingcontrol.entities.ParkingSpotEntity;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ParkingSpotService {

    @Autowired
    ParkingSpotRepository repository;

    @Transactional
    public ParkingSpotEntity save(ParkingSpotEntity parkingSpotEntity){
        return repository.save(parkingSpotEntity);
    }

    public boolean existsByLicensePlateCar(String licensePlateCar){
        return repository.existsByLicensePlateCar(licensePlateCar);
    }

    public boolean existsByApartmentAndBlock(String apartment, String block){
        return repository.existsByApartmentAndBlock(apartment, block);
    }

    public boolean existsByParkingSpotNumber(String parkingSpotNumber){
        return repository.existsByParkingSpotNumber(parkingSpotNumber);
    }
}
