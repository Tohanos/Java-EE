package com.kravchenko.javaspringbootlessonfour.dto;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
}
