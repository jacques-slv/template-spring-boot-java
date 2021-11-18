package service;

import model.Product;
import model.User;

import java.util.List;

public class SqlServerServiceImpl implements ISqlServerService {


    @Override
    public List<Product> getProducts(String searchString) {
        return null;
    }

    @Override
    public boolean addNewProduct(Product product) {
        return false;
    }

    @Override
    public boolean deleteProducts(int productId) {
        return false;
    }

    @Override
    public boolean updateProduct(Product product) {
        return false;
    }

    @Override
    public User getUser(String userName) {
        return null;
    }

    @Override
    public boolean addUser(User newUser) {
        return false;
    }

    @Override
    public boolean removeUser(int userId) {
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }
}
