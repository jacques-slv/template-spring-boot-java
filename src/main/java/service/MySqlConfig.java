package service;

public class MySqlConfig {

    private String  connectionString;
    private String  port;
    private String  userName;
    private String  password;

    public MySqlConfig(String connectionString, String userName, String password) {
        this.connectionString = connectionString;
        this.port = port;
        this.userName = userName;
        this.password = password;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
