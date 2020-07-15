package com.contentserv.controller;

import com.contentserv.model.Product;
import com.contentserv.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    @ApiOperation(value = "fetches list of products matching for given search words")
    public List<Product> fetchProducts(@RequestParam("s") String searchStr) {
        return productService.searchProducts(searchStr);
    }

}
