package com.example.nestedentities.controller;

import com.example.nestedentities.dto.CarDto;
import com.example.nestedentities.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1.0/cars")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping("/")
    public List<CarDto> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/{carId}")
    public CarDto getCarById(@PathVariable("carId") Long carId) {
        return carService.getCarById(carId);
    }
}
