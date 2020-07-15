package com.contentserv.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Document(value = "products")
public class Product {

    @Id
    String sku;

    @NotNull(message = "Name cannot be empty")
    @Size(max = 100, message = "Name cannot be more than 100 characters")
    String name;

    @NotNull(message = "Brand cannot be empty")
    String brand;

    @NotNull(message = "Category cannot be empty")
    Category category;

    @NotNull(message = "MRP cannot be empty")
    Double mrp;

    @NotNull(message = "Sale Price cannot be empty")
    Double salePrice;

    @NotNull(message = "Description cannot be empty")
    @Size(max = 400, message = "Description cannot be more than 400 characters")
    List<String> description;

    List<Attribute> attributes;
}
