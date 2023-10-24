package com.api.parkingcontrol.service;

import com.api.parkingcontrol.entities.ParkingSpotEntity;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingSpotService {

    @Autowired
    ParkingSpotRepository repository;

    @Transactional
    public ParkingSpotEntity save(ParkingSpotEntity parkingSpotEntity){
        return repository.save(parkingSpotEntity);
    }

    @Transactional
    public void delete(ParkingSpotEntity parkingSpotEntity){
        repository.delete(parkingSpotEntity);
    }

    public Page<ParkingSpotEntity> getAllParkingSpot(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Optional<ParkingSpotEntity> findById(UUID id){
        return repository.findById(id);
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
