package com.kravchenko.javaspringbootlessonfour.services;

import com.kravchenko.javaspringbootlessonfour.dto.ProductDTO;
import com.kravchenko.javaspringbootlessonfour.entities.Product;
import com.kravchenko.javaspringbootlessonfour.repositories.ProductRepository;
import com.kravchenko.javaspringbootlessonfour.repositories.specifications.ProductSpecification;
import com.kravchenko.javaspringbootlessonfour.utils.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
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
    public List<Product> getAll() {
        return productRepository.findAll();
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

    public ProductDTO save(ProductDTO productDTO) {
        Product product = ProductMapper.MAPPER.toProduct(productDTO);
        product = productRepository.save(product);
        return ProductMapper.MAPPER.fromProduct(product);
    }

    public List<ProductDTO> findAll() {
        return ProductMapper.MAPPER.fromProductList(productRepository.findAll());
    }

    public ProductDTO findOne(Long id) {
        return ProductMapper.MAPPER.fromProduct(productRepository.getOne(id));
    }

}
