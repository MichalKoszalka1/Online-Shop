package com.Shop.Shop.model;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@SuppressWarnings( "ALL" )
@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "product_id" )
    private Long id;
    @Column( name = "name" )
    private String name;

    @DecimalMin( "0" )
    @DecimalMax( "1000.0" )
    @Column( name = "mass" )
    private BigDecimal mass;

    @Column( name = "category" )
    private String category;

    @Column( name = "conditions" )
    private String conditions;

    @Column( name = "brand" )
    private String brand;
    @Column( name = "description", length = 1000, nullable = false )
    private String description;

    @Column( name = "quantity" )
    private int quantity;
    @NotNull
    @DecimalMin( "0.01" )
    @Column( name = "costPrice" )
    private Double costPrice;

    @NotNull
    @DecimalMin( "0.01" )
    @Column( name = "sizes", nullable = false )
    private BigDecimal sizes;


    @Lob
    @Column( columnDefinition = "MEDIUMBLOB" )
    private String image;


    public BigDecimal getSizes() {
        return sizes;
    }

    public void setSizes(BigDecimal sizes) {
        this.sizes = sizes;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {

        this.category = category;
    }

    public BigDecimal getMass() {
        return mass;
    }

    public void setMass(BigDecimal mass) {
        this.mass = mass;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mass=" + mass +
                ", category='" + category + '\'' +
                ", conditions='" + conditions + '\'' +
                ", brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", costPrice=" + costPrice +
                ", sizes=" + sizes +
                ", image='" + image + '\''
                +
                '}';
    }
}

