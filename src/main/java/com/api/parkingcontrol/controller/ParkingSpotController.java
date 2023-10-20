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
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {

    @Autowired
    ParkingSpotService service;

    @PostMapping
    public ResponseEntity<Object> saveParkingSpot(
            @RequestBody
            @Valid ParkingSpotDto parkingSpotDto
    ){
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

    @GetMapping
    public ResponseEntity<List<ParkingSpotEntity>> getAllParkingSpot(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllParkingSpot());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneParkingSpot(
            @PathVariable (value = "id") UUID id){
        Optional<ParkingSpotEntity> parkingSpotEntityOptional = service.findById(id);
        if(!parkingSpotEntityOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking spot not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotEntityOptional.get());
    }

}

