package com.contentserv.controller;

import com.contentserv.exception.ProductException;
import com.contentserv.model.Product;
import com.contentserv.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@Api
@RestController
@RequestMapping("/manage")
public class AdminController {

    @Autowired
    ProductService productService;

    @ApiOperation(value = "validates a product whether it exists or not using name parameter")
    @GetMapping("/validate")
    public Boolean validateProduct(@RequestParam("s") String productName) {
        return productService.validateProduct(productName);
    }

    @ApiOperation(value = "creates a product")
    @PostMapping("/products")
    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product) {
        boolean isProductExists = productService.validateProduct(product.getName());
        if (isProductExists){
            return new ResponseEntity<>( new ProductException(HttpStatus.CONFLICT.value(),
                        "Product already exists", new Date()), HttpStatus.CONFLICT);
        }
        productService.createProduct(product);
        return new ResponseEntity<String>("Product created successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "deletes a product for given id")
    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return "Product deleted successfully";
    }

    @ApiOperation(value = "updates a product")
    @PutMapping("/products")
    public String updateProduct(@Valid @RequestBody Product product) {
        productService.updateProduct(product);
        return "Product updated successfully";
    }

}
