package service;

import model.Product;
import model.User;

import java.sql.PreparedStatement;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlServerServiceImpl implements ISqlServerService {

    private MySqlConfig _config;
    private Connection mySqlConnection;

    public SqlServerServiceImpl(MySqlConfig config) {
        _config = config;
    }

    @Override
    public boolean connect() {
        try {
            String connectionString = String.format(_config.getConnectionString() + "?user=%s&password=%s", _config.getUserName(), _config.getPassword());
            mySqlConnection = DriverManager.getConnection(connectionString);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        }
        return true;
    }

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

        if (mySqlConnection == null) connect();
        try {
//                    newUser.getRole(),newUser.getUserName() newUser.getFirstName(), newUser.getLastName(), );
            PreparedStatement stmt = mySqlConnection.prepareStatement("insert into user (role, username, firstname, lastname) values(?,?,?,?)");
            stmt.setString(1,newUser.getRole());
            stmt.setString(2,newUser.getUserName());
            stmt.setString(3,newUser.getFirstName());
            stmt.setString(4,newUser.getLastName());

            int i=stmt.executeUpdate();
            System.out.println(i+" records inserted");


            mySqlConnection.close();
            System.out.println("Connection closed: " + mySqlConnection.isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean removeUser(int userId) {
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    public MySqlConfig get_config() {
        return _config;
    }
}
