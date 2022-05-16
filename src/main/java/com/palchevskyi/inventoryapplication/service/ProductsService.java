package com.palchevskyi.inventoryapplication.service;

import java.util.List;
import java.util.Map;

public interface ProductsService {
    Map<String, Boolean> getProducts(List<String> uniqIds);
}
