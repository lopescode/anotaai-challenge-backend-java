package com.lopes.anotaai_challenge.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lopes.anotaai_challenge.domain.category.Category;

/**
 *
 * @author Lopes
 */
@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
}
