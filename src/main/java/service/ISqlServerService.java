package service;

import model.Product;
import model.User;

import java.util.List;

public interface ISqlServerService {

    /**
     * Set up the connection to MySql server
     * @return
     */
    public boolean connect();

    /**
     * Disconnect from MySql server
     * @return
     */
    public boolean disconnect();

    /**
     * Retrieve all products matching the search string
     * @param searchString
     * @return
     */
    public List<Product> getProduct(String searchString);

    /**
     * Add the given list of products to the database
     * @param product
     * @return
     */
    public String addProduct(Product product);

    /**
     * Remove from the product table the item matching the give product ID
     * @param productName
     * @return
     */
    public boolean deleteProduct(String productName);

    /**
     * Update an existing product - delete and add/create - or select existing one and update fields.
     * @param product
     * @return
     */
    public String updateProduct(Product product);

    /**
     * Retrieve the User matching the supplied userName
     * @param userName
     * @return
     */
    public List<User> getUser(String userName);

    /**
     * Add the new user to the database
     * @param newUser
     * @return
     */
    public String addUser(User newUser)throws Exception;

    /**
     * Remove the user matching the given ID
     * @param userId
     * @return
     */
    public boolean deleteUser(String userId);

    /**
     * Update an existing user - delete and add/create - or select existing one and update fields.
     * @param user
     * @return
     */
    public String updateUser(User user);

}
