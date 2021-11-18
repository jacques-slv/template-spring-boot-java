package service;

import model.Product;
import model.User;

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

    /**
     * Remove from the product table the item matching the give product ID
     * @param productId
     * @return
     */
    public boolean deleteProducts(int productId);

    /**
     * Add the new user to the database
     * @param newUser
     * @return
     */
    public boolean addUser(User newUser);

}
