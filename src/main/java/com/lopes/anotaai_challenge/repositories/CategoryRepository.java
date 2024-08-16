package com.lopes.anotaai_challenge.repositories;

import com.lopes.anotaai_challenge.domain.category.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
}
