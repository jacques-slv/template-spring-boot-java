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
            if(!mySqlConnection.isClosed()) mySqlConnection.close();
            System.out.println("Connection closed: " + mySqlConnection.isClosed());

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
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
    public List<User> getUser(String userName) {
        List<User> result = new LinkedList<>();
        try {
                if (mySqlConnection == null || mySqlConnection.isClosed()) connect();

                PreparedStatement prepareStatement = mySqlConnection.prepareStatement("select * from user where username LIKE '%?%';");
                prepareStatement.setString(1, userName);

                ResultSet resultSet = prepareStatement.executeQuery();
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            System.out.println("Result Set: " + resultSet.getArray("username"));
                while (resultSet.next())
                {
                    System.out.println("Column count: " + resultSetMetaData.getColumnCount());
                    result.add(new User(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)));
                }
            } catch (SQLException e){
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                return  null;
            }
            finally {
                disconnect();
                return result;
            }

    }

    @Override
    public String addUser(User newUser) {

        try {
            if (mySqlConnection == null || mySqlConnection.isClosed()) connect();
//                    newUser.getRole(),newUser.getUserName() newUser.getFirstName(), newUser.getLastName(), );
            PreparedStatement stmt = mySqlConnection.prepareStatement("insert into user (role, username, firstname, lastname) values(?,?,?,?)");
            stmt.setString(1,newUser.getRole());
            stmt.setString(2,newUser.getUserName());
            stmt.setString(3,newUser.getFirstName());
            stmt.setString(4,newUser.getLastName());

            int i = stmt.executeUpdate();
            System.out.println(i+" records inserted");
            System.out.println("Connection closed: " + mySqlConnection.isClosed());
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            return  "user" + newUser.getUserName()+ " could not be added. MySql returned: "+e.getMessage();
        }
        finally {
            disconnect();
        }
        // happy path. User added successfully
        return "user " + newUser.getUserName()+ " has been added";
    }

    @Override
    public String deleteUser(String username) {
        try {
            if (mySqlConnection == null || mySqlConnection.isClosed()) connect();

            PreparedStatement stmt = mySqlConnection.prepareStatement("delete from user where username = ?;");
            stmt.setString(1, username);

            int i = stmt.executeUpdate();
            System.out.println(i+" records updated");
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            return  "user" + username + " could not be added. MySql returned: "+e.getMessage();
        }
        finally {
            disconnect();
        }
        // happy path. User added successfully
        return "user " + username+ " has been delete successfully";
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    public MySqlConfig get_config() {
        return _config;
    }
}
