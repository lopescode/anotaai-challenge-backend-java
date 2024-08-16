package com.lopes.anotaai_challenge.services;

import com.lopes.anotaai_challenge.domain.category.exception.CategoryNotFoundException;
import com.lopes.anotaai_challenge.domain.category.Category;
import com.lopes.anotaai_challenge.domain.category.CategoryDTO;
import com.lopes.anotaai_challenge.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category insert(CategoryDTO categoryDTO) {
        Category category = new Category(categoryDTO);

        return categoryRepository.save(category);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category update(String id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);

        if(!categoryDTO.title().isEmpty()) category.setTitle(categoryDTO.title());
        if(!categoryDTO.description().isEmpty()) category.setDescription(categoryDTO.description());

        return categoryRepository.save(category);
    }

    public void delete(String id) {
        Category category = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);

        categoryRepository.delete(category);
    }
}
