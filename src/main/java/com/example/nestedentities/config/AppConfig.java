package com.example.nestedentities.config;

import com.example.nestedentities.repository.CarRepository;
import com.example.nestedentities.repository.OwnerRepository;
import com.example.nestedentities.service.CarService;
import com.example.nestedentities.service.OwnerService;
import com.example.nestedentities.service.impl.CarServiceImpl;
import com.example.nestedentities.service.impl.OwnerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public CarService carService(OwnerService ownerService, CarRepository carRepository) {
        return new CarServiceImpl(ownerService, carRepository);
    }

    @Bean
    public OwnerService ownerService(OwnerRepository ownerRepository) {
        return new OwnerServiceImpl(ownerRepository);
    }
}
