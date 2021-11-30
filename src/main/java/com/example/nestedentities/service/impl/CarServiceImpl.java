package com.example.nestedentities.service.impl;

import com.example.nestedentities.dto.CarDto;
import com.example.nestedentities.model.Car;
import com.example.nestedentities.model.Owner;
import com.example.nestedentities.repository.CarRepository;
import com.example.nestedentities.service.CarService;
import com.example.nestedentities.service.OwnerService;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CarServiceImpl implements CarService {
    private CarRepository carRepository;
    private OwnerService ownerService;

    public CarServiceImpl(OwnerService ownerService, CarRepository carRepository) {
        this.ownerService = ownerService;
        this.carRepository = carRepository;
    }

    @Override
    public void save(Car car) {
        carRepository.save(car);
    }

    @Override
    public CarDto getCarById(Long carId) {
        Car car = carRepository.findCarById(carId);
        return car != null ? toCarDto(car) : null;
    }

    @Override
    public List<CarDto> getAllCars() {
        return carRepository.findAll().stream()
                .map(CarServiceImpl::toCarDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarDto> getCarsByOwnerId(Long ownerId) {
        Owner owner = ownerService.getOwnerByOwnerId(ownerId);
        if (owner == null) {
            throw new RuntimeException("No one owner was found with current id");
        }
        List<Car> cars = owner.getCars();
        return CollectionUtils.isEmpty(cars)
                ? null
                : cars.stream()
                .map(CarServiceImpl::toCarDto)
                .collect(Collectors.toList());
    }

    @Override
    public CarDto getCarByOwnerIdAndCarId(Long ownerId, Long carId) {
        Owner owner = ownerService.getOwnerByOwnerId(ownerId);
        if (owner == null) {
            throw new RuntimeException("No one owner was found with current id");
        }

        List<Car> cars = owner.getCars();

        Car car = null;

        if (cars != null) {
            car = cars.stream()
                    .filter(x -> Objects.equals(x.getId(), carId))
                    .findFirst()
                    .orElse(null);
        }

        if (car == null) {
            throw new RuntimeException("No one car was found with current id");
        }

        return toCarDto(car);
    }

    @Override
    public void createCarForOwner(Owner owner, CarDto carDto) {
        Car car = new Car();
        car.setOwner(owner);
        car.setName(carDto.getName());

        save(car);
    }

    public static CarDto toCarDto(Car car) {
        CarDto carDto = new CarDto();
        carDto.setName(car.getName());
        carDto.setOwner(car.getOwner());
        carDto.setId(car.getId());
        return carDto;
    }
}
