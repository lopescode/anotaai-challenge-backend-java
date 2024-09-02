package com.lopes.anotaai_challenge.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lopes.anotaai_challenge.domain.category.Category;
import com.lopes.anotaai_challenge.domain.category.CategoryDTO;
import com.lopes.anotaai_challenge.domain.category.exception.CategoryNotFoundException;
import com.lopes.anotaai_challenge.repositories.CategoryRepository;
import com.lopes.anotaai_challenge.services.aws.AwsSnsService;
import com.lopes.anotaai_challenge.services.aws.MessageDTO;

/**
 *
 * @author Lopes
 */
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final AwsSnsService snsService;

    public CategoryService(CategoryRepository categoryRepository, AwsSnsService snsService) {
        this.categoryRepository = categoryRepository;
        this.snsService = snsService;
    }

    public Category insert(CategoryDTO categoryDTO) {
        Category category = new Category(categoryDTO);

        categoryRepository.save(category);

        this.snsService.publish(new MessageDTO(categoryDTO.toString()));

        return category;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(String id) {
        return categoryRepository.findById(id);
    }

    public Category update(String id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);

        if(!categoryDTO.title().isEmpty()) category.setTitle(categoryDTO.title());
        if(!categoryDTO.description().isEmpty()) category.setDescription(categoryDTO.description());

        categoryRepository.save(category);

        this.snsService.publish(new MessageDTO(categoryDTO.toString()));

        return category;
    }

    public void delete(String id) {
        Category category = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);

        categoryRepository.delete(category);
    }
}
