package com.kravchenko.javaspringbootlessonfour.utils;

import com.kravchenko.javaspringbootlessonfour.dto.ProductDTO;
import com.kravchenko.javaspringbootlessonfour.entities.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    Product toProduct(ProductDTO productDTO);

    @InheritInverseConfiguration
    ProductDTO fromProduct(Product product);

    List<Product> toProductList(List<ProductDTO> productDTOS);

    List<ProductDTO> fromProductList(List<Product> products);

}
