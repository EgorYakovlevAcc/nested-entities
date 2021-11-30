package com.example.nestedentities.service;

import com.example.nestedentities.dto.OwnerDto;
import com.example.nestedentities.model.Owner;

import java.util.List;

public interface OwnerService {
    void save(Owner owner);

    List<OwnerDto> getAllOwners();

    OwnerDto getOwnerDtoByOwnerId(Long ownerId);

    Owner getOwnerByOwnerId(Long ownerId);

    void createOwner(OwnerDto ownerDto);
}
