package com.lopes.anotaai_challenge.repositories;

import com.lopes.anotaai_challenge.domain.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
