package com.kravchenko.javaspringbootlessonfour.rest;

import com.kravchenko.javaspringbootlessonfour.dto.ProductDTO;
import com.kravchenko.javaspringbootlessonfour.entities.Product;
import com.kravchenko.javaspringbootlessonfour.services.ProductService;
import com.kravchenko.javaspringbootlessonfour.services.exceptions.NotFoundException;
import com.kravchenko.javaspringbootlessonfour.utils.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
public class ProductRestController {

    private ProductService service;

    @Autowired
    public void setService(ProductService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}/id", produces = "application/json")
    public ProductDTO findById(@PathVariable("id") Long id) {
        Product product = service.getById(id).orElseThrow(NotFoundException::new);
        return ProductMapper.MAPPER.fromProduct(product);
    }

    @GetMapping(path = "/list", produces = "application/json")
    public List<ProductDTO> findAll() {
        return service.findAll();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
        Product product = ProductMapper.MAPPER.toProduct(productDTO);
        service.addOrUpdate(product);
        return ProductMapper.MAPPER.fromProduct(product);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO) {
        Product product = ProductMapper.MAPPER.toProduct(productDTO);
        service.addOrUpdate(product);
        return ProductMapper.MAPPER.fromProduct(product);
    }

    @DeleteMapping("/{id}/id")
    public void deleteById(@PathVariable("id") Long id) {
        service.remove(id);
    }

    @ExceptionHandler
    public ResponseEntity<String> notFoundExceptionHandler(NotFoundException e) {
        return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
    }


}
