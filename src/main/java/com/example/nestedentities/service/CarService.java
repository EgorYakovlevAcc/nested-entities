package com.example.nestedentities.service;

import com.example.nestedentities.dto.CarDto;
import com.example.nestedentities.model.Car;
import com.example.nestedentities.model.Owner;

import java.util.List;

public interface CarService {
    void save(Car car);

    CarDto getCarById(Long carId);

    List<CarDto> getAllCars();

    List<CarDto> getCarsByOwnerId(Long ownerId);

    CarDto getCarByOwnerIdAndCarId(Long ownerId, Long carId);

    void createCarForOwner(Owner owner, CarDto carDto);
}
