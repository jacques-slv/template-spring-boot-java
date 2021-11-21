package service;

import model.Product;
import model.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

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
    public boolean disconnect() {
        try {
            if (!mySqlConnection.isClosed()) mySqlConnection.close();
            System.out.println("Connection closed: " + mySqlConnection.isClosed());

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            return false;
        }
        return true;
    }

    @Override
    public List<Product> getProduct(String productName) {
        List<Product> result = new LinkedList<>();
        try {
            if (mySqlConnection == null || mySqlConnection.isClosed()) connect();

            String searchString = "'%" + productName + "%'";
            String query = "select * from product where name LIKE " + searchString + ";";
            PreparedStatement prepareStatement = mySqlConnection.prepareStatement(query);

            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Role: " + resultSet.getString("role"));
                //String productName, String productDescription, Double price
                result.add(new Product(
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price")));
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            return null;
        } finally {
            disconnect();
            if(result.isEmpty())return null;
            return result;
        }
    }

    @Override
    public String addProduct(Product product) {
        int rowInserted = 0;
        try {
            if (mySqlConnection == null || mySqlConnection.isClosed()) connect();
            PreparedStatement preparedStatement = mySqlConnection.prepareStatement("insert into product (name, description, price) values(?,?,?)");
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getProductDescription());
            preparedStatement.setDouble(3, product.getPrice());

            rowInserted = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            disconnect();
            // if we have an error we return the error message
            return e.getMessage();
        }
        disconnect();
        // happy path. Product added successfully; Return the string representing the product
        return "Execution returned {"+ rowInserted +"} rows inserted for"+ product.toString();
    }

    @Override
    public boolean deleteProduct(String productName) {
        try {
            if (mySqlConnection == null || mySqlConnection.isClosed()) connect();

            PreparedStatement stmt = mySqlConnection.prepareStatement("delete from product where name = ?;");
            stmt.setString(1, productName);

            int i = stmt.executeUpdate();
            System.out.println(i + " records updated");
            if(i == 0){return false;}
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            return false;
        } finally {
            disconnect();
        }
        // happy path. User deleted successfully
        return true;
    }

    @Override
    public String updateProduct(Product product) {
        int rowInserted = 0;
        try {
            if (mySqlConnection == null || mySqlConnection.isClosed()) connect();
            PreparedStatement preparedStatement = mySqlConnection.prepareStatement("update product set description = ?, price = ? where name = ?;");
            preparedStatement.setString(1, product.getProductDescription());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setString(3, product.getProductName());

            rowInserted = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            disconnect();
            // if we have an error we return the error message
            return e.getMessage();
        }
        disconnect();
        // happy path. Product added successfully; Return the string representing the product
        return "Execution returned {"+ rowInserted +"} rows inserted for"+ product.toString();
    }

    @Override
    public List<User> getUser(String userName) {
        List<User> result = new LinkedList<>();
        try {
            if (mySqlConnection == null || mySqlConnection.isClosed()) connect();

            String searchString = "'%" + userName + "%'";
            String query = "select * from user where username LIKE " + searchString + ";";
            PreparedStatement prepareStatement = mySqlConnection.prepareStatement(query);

            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Role: " + resultSet.getString("role"));
                //String userName, String firstName, String lastName, String role
                result.add(new User(
                        //role, username, firstname, lastname
                        resultSet.getString("username"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("role")));
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            return null;
        } finally {
            disconnect();
            if(result.isEmpty())return null;
            return result;
        }

    }

    @Override
    public String addUser(User newUser) {
        int rowInserted = 0;
        try {
            if (mySqlConnection == null || mySqlConnection.isClosed()) connect();
            PreparedStatement preparedStatement = mySqlConnection.prepareStatement("insert into user (role, username, firstname, lastname) values(?,?,?,?)");
            preparedStatement.setString(1, newUser.getRole());
            preparedStatement.setString(2, newUser.getUserName());
            preparedStatement.setString(3, newUser.getFirstName());
            preparedStatement.setString(4, newUser.getLastName());
            rowInserted = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            disconnect();
            // if we have an error we return the error message
            return e.getMessage();
        }
        disconnect();
        // happy path. User added successfully; return the string representing the user
        return "Execution returned {"+ rowInserted +"} rows inserted for"+ newUser.toString();
    }

    @Override
    public boolean deleteUser(String username) {
        try {
            if (mySqlConnection == null || mySqlConnection.isClosed()) connect();

            PreparedStatement stmt = mySqlConnection.prepareStatement("delete from user where username = ?;");
            stmt.setString(1, username);

            int i = stmt.executeUpdate();
            System.out.println(i + " records updated");
            if(i == 0){return false;}
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            return false;
        } finally {
            disconnect();
        }
        // happy path. User added successfully
        return true;
    }

    @Override
    public String updateUser(User user) {
        int rowInserted = 0;
        try {
            if (mySqlConnection == null || mySqlConnection.isClosed()) connect();
            PreparedStatement preparedStatement = mySqlConnection.prepareStatement("update user set role = ?, firstname = ?, lastname = ? where username = ?;");
            preparedStatement.setString(1, user.getRole());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getUserName());
            rowInserted = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            disconnect();
            // if we have an error we return the error message
            return e.getMessage();
        }
        disconnect();
        // happy path. User added successfully; return the string representing the user
        return "Execution returned {"+ rowInserted +"} rows inserted for "+ user.toString();
    }


    public MySqlConfig get_config() {
        return _config;
    }
}
