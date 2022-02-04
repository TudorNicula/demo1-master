package com.example.demo.entity.DTO;

import com.example.demo.entity.Product;

import java.util.Objects;

public class ProductDTO extends Product {

    private Long id;
    private String title;
    private String description;
    private Long price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProductDTO courseDTO = (ProductDTO) o;
        return id.equals(courseDTO.id) &&
                Objects.equals(title, courseDTO.title) &&
                Objects.equals(description, courseDTO.description) &&
                Objects.equals(price, courseDTO.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, title, description, price);
    }

}
