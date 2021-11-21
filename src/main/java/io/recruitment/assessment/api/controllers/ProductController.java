package io.recruitment.assessment.api.controllers;

import model.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.SqlServerServiceImpl;

import java.util.List;
import java.util.Map;


@RestController
public class ProductController {

    private SqlServerServiceImpl _sqlServerService;

    public ProductController(SqlServerServiceImpl sqlServerService) {
        _sqlServerService = sqlServerService;
    }


    @RequestMapping("/addproduct/{productname}/{description}/{price}/")
    Map<String, String> addProduct(@PathVariable String productname, @PathVariable String description, @PathVariable Double price) {

        Product newProduct = new Product(productname, description, price);
        String result = _sqlServerService.addNewProduct(newProduct);
        return Map.of("message", result);
    }

    @RequestMapping("/getproduct/{productname}")
    Map<String, List<Product>> getProduct(@PathVariable String productname) {

        List<Product> products = _sqlServerService.getProduct(productname);
        return Map.of("message", products);
    }

    @RequestMapping("/deletepoduct/{productname}")
    Map<String, String> deleteProduct(@PathVariable String productname) {

        if (_sqlServerService.deleteUser(productname)) {

            return Map.of("message", "Product {" + productname + "} has been successfully deleted");
        }
        return Map.of("message", "Product {" + productname + "} could not be deleted");
    }
}
