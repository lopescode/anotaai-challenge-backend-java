package com.lopes.anotaai_challenge.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lopes.anotaai_challenge.domain.category.Category;
import com.lopes.anotaai_challenge.domain.category.exception.CategoryNotFoundException;
import com.lopes.anotaai_challenge.domain.product.Product;
import com.lopes.anotaai_challenge.domain.product.ProductDTO;
import com.lopes.anotaai_challenge.domain.product.exception.ProductNotFoundException;
import com.lopes.anotaai_challenge.repositories.ProductRepository;
import com.lopes.anotaai_challenge.services.aws.AwsSnsService;
import com.lopes.anotaai_challenge.services.aws.MessageDTO;

/**
 *
 * @author Lopes
 */
@Service
public class ProductService {
  private final CategoryService categoryService;
  private final AwsSnsService snsService;

  private final ProductRepository productRepository;

  public ProductService(CategoryService categoryService, ProductRepository productRepository, AwsSnsService snsService) {
    this.categoryService = categoryService;
    this.productRepository = productRepository;
    this.snsService = snsService;
  }

  public Product insert(ProductDTO productDTO) {
    Category category = this.categoryService.findById(productDTO.categoryId()).orElseThrow(CategoryNotFoundException::new);

    Product product = new Product(productDTO);

    product.setCategory(category);

    productRepository.save(product);
    
    this.snsService.publish(new MessageDTO(productDTO.ownerId()));

    return product;
  }

  public List<Product> findAll() {
    return productRepository.findAll();
  }

  public Product update(String id, ProductDTO productDTO) {
    Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);

    if (productDTO.categoryId() != null) {
      this.categoryService.findById(productDTO.categoryId()).ifPresent(product::setCategory);
    }

    if (!productDTO.title().isEmpty()) product.setTitle(productDTO.title());
    if (!productDTO.description().isEmpty()) product.setDescription(productDTO.description());
    if (!(productDTO.price() == null)) product.setPrice(productDTO.price());

    productRepository.save(product);

    this.snsService.publish(new MessageDTO(productDTO.ownerId()));

    return product;
  }

  public void delete(String id) {
    Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);

    productRepository.delete(product);
  }
}
