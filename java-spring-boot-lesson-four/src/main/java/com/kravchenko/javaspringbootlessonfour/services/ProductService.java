package com.kravchenko.javaspringbootlessonfour.services;

import com.kravchenko.javaspringbootlessonfour.entities.Product;
import com.kravchenko.javaspringbootlessonfour.repositories.ProductRepository;
import com.kravchenko.javaspringbootlessonfour.repositories.specifications.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Transactional
    public Product getById(Long id) {
        return productRepository.findById(id).get();
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
    public List<Product> getByTitle(String nameFilter) {

        Specification<Product> specification = Specification.where(null);
        specification = specification.and(ProductSpecification.titleLike(nameFilter));

        return productRepository.findAll(specification);
    }

    @Transactional
    public List<Product> getByMinMaxCriteria(BigDecimal minFilter, BigDecimal maxFilter) {

        Specification<Product> specification = Specification.where(ProductSpecification.trueLiteral());
        specification = specification.and(ProductSpecification.minMaxSelection(minFilter, maxFilter));

        return productRepository.findAll(specification);
    }

}
