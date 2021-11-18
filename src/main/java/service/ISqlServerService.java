package service;

import model.Product;

import java.util.List;

public interface ISqlServerService {


    /**
     * Retrieve all products matching the search string
     * @param searchString
     * @return
     */
    public List<Product> getProducts(String searchString);

    /**
     * Add the given list of products to the database
     * @param products
     * @return
     */
    public boolean addNewProduct(List<Product> products);

}
