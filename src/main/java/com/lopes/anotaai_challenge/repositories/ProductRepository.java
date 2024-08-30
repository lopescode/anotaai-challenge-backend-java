package com.lopes.anotaai_challenge.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lopes.anotaai_challenge.domain.product.Product;

/**
 *
 * @author Usuario
 */
@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
