package service;

import model.User;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SqlServerServiceImplTest {

    SqlServerServiceImpl sqlServerService;

    @BeforeEach
    void setUp() {
        sqlServerService = new SqlServerServiceImpl(new MySqlConfig("jdbc:mysql://localhost:3306/assessment", "db_user", "8b3b91c7"));
    }


    @AfterEach
    void cleanUp() {
        deleteTestUserFromDB();

    }

    @Test
    @DisplayName("Verify that the new User can be added successfully")
    void userCanBeAddedSuccessfully() {
        String expectedResult = "Execution returned {1} rows inserted forUser{userName='testuser', firstName='test', lastName='user', role='admin'}";
        assertEquals(expectedResult, sqlServerService.addUser(new User("testuser", "test", "user", "admin")),
                "The result is not as expected");

    }

    @Test
    @DisplayName("Verify that the new User cannot be added as a user with the same username already exists!")
    void userAlreadyExists() {
        addTestUserToDB();
        String expectedResult = "Duplicate entry 'testuser' for key 'user.username'";
        assertEquals(expectedResult, sqlServerService.addUser(new User("testuser", "test", "user", "admin")),
                "The result is not as expected");

    }

    @Test
    @DisplayName("Verify that the new User can be retrieved successfully")
    void userCanBeRetrievedSuccessfully() {

        User expectedUser = new User("john.blog", "john", "blog", "CUSTOMER");
        User actualUser = sqlServerService.getUser("john.blog").get(0);
        assertEquals(expectedUser.getUserName(), actualUser.getUserName(),
                "The result is not the expected result");
        assertEquals(expectedUser.getFirstName(), actualUser.getFirstName(),
                "The result is not the expected result");
        assertEquals(expectedUser.getLastName(), actualUser.getLastName(),
                "The result is not the expected result");
        assertEquals(expectedUser.getRole(), actualUser.getRole(),
                "The result is not the expected result");
    }
    @Test
    @DisplayName("Verify that the user deosn't exist in the DB")
    void userDoesNoExist() {

        assertEquals(null, sqlServerService.getUser("tester"),
                "The result should be null");
    }


    @Test
    @DisplayName("Verify that the new User can be deleted successfully")
    void userCanBeDeletedSuccessfully() {
        addTestUserToDB();
        assertTrue(sqlServerService.deleteUser("testuser"));
    }

    @Test
    @DisplayName("Verify that the new User cannot be deleted successfully")
    void userCannotBeDeletedSuccessfully() {
        addTestUserToDB();
        assertFalse(sqlServerService.deleteUser("tester"));
    }


    /************************************************
     * Support methods to create and delete test user
     ************************************************/
    void addTestUserToDB() {
        User testUser = new User("testuser", "test", "user", "admin");
        sqlServerService.addUser(testUser);
    }

    void deleteTestUserFromDB() {
        sqlServerService.deleteUser("testuser");
    }
}