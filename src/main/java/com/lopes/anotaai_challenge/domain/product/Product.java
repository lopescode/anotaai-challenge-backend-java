package com.lopes.anotaai_challenge.domain.product;

import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Lopes
 */
@Document(collection = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    private String id;
    private String title;
    private String description;
    private String ownerId;
    private String categoryId;
    private Integer price; // All prices are in cents

    public Product(ProductDTO productDTO) {
        this.title = productDTO.title();
        this.description = productDTO.description();
        this.ownerId = productDTO.ownerId();
        this.price = productDTO.price();
        this.categoryId = productDTO.categoryId();
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        
        json.put("id", this.id);
        json.put("title", this.title);
        json.put("description", this.description);
        json.put("ownerId", this.ownerId);
        json.put("categoryId", this.categoryId);
        json.put("price", this.price);
        json.put("type", "product");

        return json.toString();
    }
}
