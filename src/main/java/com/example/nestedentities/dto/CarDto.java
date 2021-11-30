package com.example.nestedentities.dto;

import com.example.nestedentities.model.Owner;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private Long id;
    private String name;
    @JsonBackReference
    private Owner owner;
}
