package com.palchevskyi.inventoryapplication.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.palchevskyi.inventoryapplication.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductsServiceImpl implements ProductsService {
    private static List<Product> PRODUCTS_LIST;

    @PostConstruct
    private void initInMemoryData() {
        String PATH_TO_CSV_FILE = "jcpenney_com-ecommerce_sample.csv";
        File file = new File(PATH_TO_CSV_FILE);

        try {
            MappingIterator<Product> personIter = new CsvMapper().readerWithTypedSchemaFor(Product.class).readValues(file);
            PRODUCTS_LIST = personIter.readAll();
        } catch (Exception e) {
            System.out.println("Error while reading data from file " + PATH_TO_CSV_FILE + ". Stack trace " +e.getMessage());
        }
    }

    public Map<String, Boolean> getProducts(List<String> uniqIds) {
        if (CollectionUtils.isEmpty(uniqIds)) {
            return new HashMap<>();
        } else {
            Map<String, Boolean> result = new HashMap<>();

            List<Product> filteredList =  PRODUCTS_LIST.stream().filter(product -> uniqIds.contains(product.getUniqId())).collect(Collectors.toList());
            filteredList.forEach(product -> result.put(product.getUniqId(), new Random().nextBoolean()));

            return result;
        }
    }
}
