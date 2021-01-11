package com.signore.SpringBootEx.services;

import com.signore.SpringBootEx.entities.Product;
import com.signore.SpringBootEx.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class ProductsService {
    private ProductsRepository productsRepository;

    @Autowired
    public void setProductsRepository(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }


    public List<Product> index() {
        return productsRepository.findAll();
    }

    public void addProduct(Product product) {
        productsRepository.save(product);
    }

    public Product findById(Long id) {
        return productsRepository.getOne(id);
    }

    @Transactional
    public void editProduct(Long id, Product updatedProduct) {
        productsRepository.editProduct(id, updatedProduct.getTitle(), updatedProduct.getPrice());
    }

    public void deleteProduct(Long id){
        productsRepository.deleteById(id);
    }



    public Page<Product> getProductsWithPagingAdnFiltering(Specification<Product> productSpecification, Pageable pageable){
        return productsRepository.findAll(productSpecification, pageable);
    }
}
