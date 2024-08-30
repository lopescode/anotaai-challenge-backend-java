package com.lopes.anotaai_challenge.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lopes.anotaai_challenge.domain.category.Category;
import com.lopes.anotaai_challenge.domain.category.exception.CategoryNotFoundException;
import com.lopes.anotaai_challenge.domain.product.Product;
import com.lopes.anotaai_challenge.domain.product.ProductDTO;
import com.lopes.anotaai_challenge.domain.product.exception.ProductNotFoundException;
import com.lopes.anotaai_challenge.repositories.ProductRepository;

/**
 *
 * @author Usuario
 */
@Service
public class ProductService {
  private final CategoryService categoryService;

  private final ProductRepository productRepository;

  public ProductService(CategoryService categoryService, ProductRepository productRepository) {
    this.categoryService = categoryService;
    this.productRepository = productRepository;
  }

  public Product insert(ProductDTO productDTO) {
    Category category = this.categoryService.findById(productDTO.categoryId()).orElseThrow(CategoryNotFoundException::new);

    Product product = new Product(productDTO);

    product.setCategory(category);

    return productRepository.save(product);
  }

  public List<Product> findAll() {
    return productRepository.findAll();
  }

  public Product update(String id, ProductDTO productDTO) {
    Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);

    this.categoryService.findById(productDTO.categoryId()).ifPresent(product::setCategory);

    if (!productDTO.title().isEmpty()) product.setTitle(productDTO.title());
    if (!productDTO.description().isEmpty()) product.setDescription(productDTO.description());
    if (!(productDTO.price() == null)) product.setPrice(productDTO.price());

    return productRepository.save(product);
  }

  public void delete(String id) {
    Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);

    productRepository.delete(product);
  }
}
