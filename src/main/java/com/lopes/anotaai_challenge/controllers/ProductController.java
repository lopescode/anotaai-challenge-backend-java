package com.lopes.anotaai_challenge.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lopes.anotaai_challenge.domain.product.Product;
import com.lopes.anotaai_challenge.domain.product.ProductDTO;
import com.lopes.anotaai_challenge.services.ProductService;

/**
 *
 * @author Lopes
 */
@RestController
@RequestMapping("/api/product")
public class ProductController {
  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping
  public ResponseEntity<Product> insert(@RequestBody ProductDTO productDTO) {
    Product newProduct = productService.insert(productDTO);

    return ResponseEntity.ok().body(newProduct);
  }

  @GetMapping
  public ResponseEntity<List<Product>> findAll() {
    List<Product> products = productService.findAll();

    return ResponseEntity.ok().body(products);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> update(@PathVariable String id, @RequestBody ProductDTO productDTO) {
    Product updatedProduct = productService.update(id, productDTO);

    return ResponseEntity.ok().body(updatedProduct);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    productService.delete(id);

    return ResponseEntity.noContent().build();
  }
}