package com.kravchenko.javaspringbootlessonfour.services;

import com.kravchenko.javaspringbootlessonfour.entities.Product;
import com.kravchenko.javaspringbootlessonfour.repositories.ProductRepository;
import com.kravchenko.javaspringbootlessonfour.repositories.specifications.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;


@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void addOrUpdate(Product product) {
        productRepository.save(product);
    }

    @Transactional
    public Page<Product> getByParams(Optional<String> nameFilter,
                                     Optional<BigDecimal> min,
                                     Optional<BigDecimal> max,
                                     Optional<Integer> page,
                                     Optional<Integer> size,
                                     Optional<String> sortField,
                                     Optional<String> sortDir) {

        Specification<Product> specification = Specification.where(null);
        if (nameFilter.isPresent()) {
            specification = specification.and(ProductSpecification.titleLike(nameFilter.get()));
        }

        if (min.isPresent()) {
            specification = specification.and(ProductSpecification.ge(min.get()));
        }

        if (max.isPresent()) {
            specification = specification.and(ProductSpecification.le(max.get()));
        }

        if (sortField.isPresent()) {
            if (sortDir.isPresent()) {
                if (sortDir.get().equals("desc")) {
                    return productRepository.findAll(specification,
                            PageRequest.of(page.orElse(1) - 1, size.orElse(4), Sort.by(sortField.get()).descending()));
                }
            }
            return productRepository.findAll(specification,
                    PageRequest.of(page.orElse(1) - 1, size.orElse(4), Sort.by(sortField.get()).ascending()));
        }
        return productRepository.findAll(specification,
                PageRequest.of(page.orElse(1) - 1, size.orElse(4)));
    }
}
