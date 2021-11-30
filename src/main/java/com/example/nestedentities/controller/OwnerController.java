package com.example.nestedentities.controller;

import com.example.nestedentities.dto.CarDto;
import com.example.nestedentities.dto.OwnerDto;
import com.example.nestedentities.service.CarService;
import com.example.nestedentities.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1.0/owners")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;
    @Autowired
    private CarService carService;

    @GetMapping("/")
    public List<OwnerDto> getAllOwners() {
        return ownerService.getAllOwners();
    }

    @PostMapping("/")
    public String createOwner(@RequestBody OwnerDto ownerDto) {
        ownerService.createOwner(ownerDto);
        return "OK";
    }

    @GetMapping("/{ownerId}")
    public OwnerDto getAllOwners(@PathVariable("ownerId") Long ownerId) {
        return ownerService.getOwnerDtoByOwnerId(ownerId);
    }

    @GetMapping("/{ownerId}/cars")
    public List<CarDto> getAllCarsByOwnerId(@PathVariable("ownerId") Long ownerId) {
        return carService.getCarsByOwnerId(ownerId);
    }

    @GetMapping("/{ownerId}/cars/{carId}")
    public CarDto getAllCarsByOwnerId(@PathVariable("ownerId") Long ownerId, @PathVariable("carId") Long carId) {
        return carService.getCarByOwnerIdAndCarId(ownerId, carId);
    }

    @PostMapping("/{ownerId}/cars")
    public String createCarForOwner(@PathVariable("ownerId") Long ownerId, @RequestBody CarDto carDto) {
        carService.createCarForOwner(ownerService.getOwnerByOwnerId(ownerId), carDto);
        return "OK";
    }
}
