package com.api.parkingcontrol.repositories;

import com.api.parkingcontrol.entities.ParkingSpotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotEntity, UUID> {

    boolean existsByLicensePlateCar(String licensePlateCar);
    boolean existsByApartmentAndBlock(String apartment, String block);
    boolean existsByParkingSpotNumber(String parkingSpotNumber);

}
