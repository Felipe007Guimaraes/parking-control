package com.api.parkingcontrol.controller;

import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.entities.ParkingSpotEntity;
import com.api.parkingcontrol.service.ParkingSpotService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {

    @Autowired
    ParkingSpotService service;

    @PostMapping
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto){
        if(service.existsByLicensePlateCar(parkingSpotDto.getLicensePlateCar())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: License Car is already in use!");
        }
        if (service.existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot is already in use!");
        }
        if (service.existsByApartmentAndBlock(parkingSpotDto.getApartment(),parkingSpotDto.getBlock())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking spot already registred for this apartment/block");
        }
        var entity = new ParkingSpotEntity();
        BeanUtils.copyProperties(parkingSpotDto,entity);
        entity.setRegistrationTime(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));

    }
}

