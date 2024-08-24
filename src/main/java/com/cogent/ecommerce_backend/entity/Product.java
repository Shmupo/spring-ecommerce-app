package com.cogent.ecommerce_backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Arrays;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(
        name = "products"
)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Title cannot be empty or null")
    private String title;
    @NotEmpty(message = "Description cannot be empty or null")
    private String description;
    @NotNull(message = "Price cannot be null")
    private int price;
    private String[] images;

    @ManyToOne (
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "category_id",
            nullable = false
    )
    @JsonBackReference
    private Category category;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", images=" + Arrays.toString(images) +
                ", category=" + category +
                '}';
    }
}
