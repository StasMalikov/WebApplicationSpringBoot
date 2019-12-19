package com.example.sweater.controller;

import com.example.sweater.Reader;
import com.example.sweater.domain.Post;
import com.example.sweater.domain.Product;
import com.example.sweater.repos.ProductRepo;
import com.example.sweater.repos.StoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private StoreRepo storeRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/products")
    public String products(Map<String, Object> model) {

        Iterable<Product> products = productRepo.findAll();
        model.put("res", products);
        return "products";
    }

    @GetMapping("/products_save")
    public String products_save(Map<String, Object> model) {

        Reader reader = new Reader("C:\\Users\\StasMalikov\\Desktop\\web_proj\\web_proj\\src\\main\\resources\\price-voronezh.xls");
        reader.ReadAndParseStores();
        reader.ReadAndParseProducts(20);
        storeRepo.saveAll(reader.getStores());
        productRepo.saveAll(reader.getProducts());


        model.put("res", null);
        return "products";
    }
}
