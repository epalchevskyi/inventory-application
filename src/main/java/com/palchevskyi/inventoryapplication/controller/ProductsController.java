package com.palchevskyi.inventoryapplication.controller;

import com.palchevskyi.inventoryapplication.model.Product;
import com.palchevskyi.inventoryapplication.service.ProductsService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("products")
public class ProductsController {
    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public Map<String, Boolean> getProducts(@RequestParam(required = false) String uniqIds) {
        List<String> parsedIds = new ArrayList<>();

        if (StringUtils.hasText(uniqIds)) {
            parsedIds = Arrays.asList(uniqIds.split(","));
        }

        return productsService.getProducts(parsedIds);
    }
}
