package com.lopes.anotaai_challenge.domain.product;

/**
 *
 * @author Lopes
 */
public record ProductDTO(String title, String description, String ownerId, Integer price, String categoryId) {
}
