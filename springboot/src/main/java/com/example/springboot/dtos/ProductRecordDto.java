package com.example.springboot.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRecordDto(
    @NotBlank
    String nome,
    @NotNull
    BigDecimal value
    ) {

}
