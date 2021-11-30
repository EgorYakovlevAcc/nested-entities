package com.example.nestedentities.service.impl;

import com.example.nestedentities.dto.OwnerDto;
import com.example.nestedentities.model.Owner;
import com.example.nestedentities.repository.OwnerRepository;
import com.example.nestedentities.service.OwnerService;

import java.util.List;
import java.util.stream.Collectors;

public class OwnerServiceImpl implements OwnerService {
    private OwnerRepository ownerRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public void save(Owner owner) {
        ownerRepository.save(owner);
    }

    @Override
    public List<OwnerDto> getAllOwners() {
        return ownerRepository.findAll().stream()
                .map(this::toOwnerDto)
                .collect(Collectors.toList());
    }

    @Override
    public OwnerDto getOwnerDtoByOwnerId(Long ownerId) {
        Owner owner = getOwnerByOwnerId(ownerId);
        return owner != null ? toOwnerDto(owner) : null;
    }

    @Override
    public Owner getOwnerByOwnerId(Long ownerId) {
        return ownerRepository.findOwnerById(ownerId);
    }

    @Override
    public void createOwner(OwnerDto ownerDto) {
        Owner owner = new Owner();
        owner.setFirstname(ownerDto.getFirstname());
        owner.setLastname(ownerDto.getLastname());

        save(owner);
    }

    private OwnerDto toOwnerDto(Owner owner) {
        OwnerDto ownerDto = new OwnerDto();
        ownerDto.setId(owner.getId());
        ownerDto.setFirstname(owner.getFirstname());
        ownerDto.setLastname(owner.getLastname());
        ownerDto.setCars(owner.getCars().stream()
                .map(CarServiceImpl::toCarDto)
                .collect(Collectors.toList()));
        return ownerDto;
    }
}
