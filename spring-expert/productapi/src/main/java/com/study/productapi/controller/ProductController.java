package com.study.productapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.productapi.model.Product;
import com.study.productapi.repository.ProductRepository;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController // faz com que a classe vire em controller (receba requests)
@RequestMapping("products") // diz qual a url base desse controller
public class ProductController {

    private ProductRepository pRepository;

    public ProductController(ProductRepository productRepository) {
        this.pRepository = productRepository;
    }

    @PostMapping("post")
    public Product saveProduct(@RequestBody Product product) {
        var id = UUID.randomUUID().toString();
        product.setId(id);
        pRepository.save(product);
        return product;
    }

    @GetMapping("get/{id}")
    public Product getById(@PathVariable("id") String id) {
        // Optional<Product> pOptional = pRepository.findById(id);
        // return pOptional.isPresent() ? pOptional.get() : null;

        return pRepository.findById(id).orElse(null);
    }

    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable("id") String id) {
        pRepository.deleteById(id);
    }

    @PutMapping("put/{id}")
    public void updateById(@PathVariable("id") String id, @RequestBody Product newProduct) {
        newProduct.setId(id);
        pRepository.save(newProduct);
    }

    @GetMapping("get")
    public List<Product> getByName(@RequestParam("name") String name) {
        return pRepository.findByName(name);
    }

}
