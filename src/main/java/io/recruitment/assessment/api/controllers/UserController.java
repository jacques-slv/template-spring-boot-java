package io.recruitment.assessment.api.controllers;

import model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.SqlServerServiceImpl;

import java.util.List;
import java.util.Map;


@RestController
public class UserController {

    private SqlServerServiceImpl _sqlServerService;

    public UserController(SqlServerServiceImpl sqlServerService) {
        _sqlServerService = sqlServerService;
    }


    @RequestMapping("/adduser/{username}/{firstname}/{lastname}/{role}/")
    Map<String, String> addUser(@PathVariable String username, @PathVariable String firstname, @PathVariable String lastname, @PathVariable String role) {

        User newUser = new User(username, firstname, lastname, role);
        String result = _sqlServerService.addUser(newUser);
        return Map.of("message", result);
    }

    @RequestMapping("/getuser/{username}")
    Map<String, List<User>> getUser(@PathVariable String username) {

        List<User> users = _sqlServerService.getUser(username);
        return Map.of("message", users);
    }

    @RequestMapping("/deleteuser/{username}")
    Map<String, String> deleteUser(@PathVariable String username) {

        if (_sqlServerService.deleteUser(username)) {

            return Map.of("message", "User {" + username + "} has bee successfully deleted");
        }
        return Map.of("message", "User {" + username + "} could not be deleted");
    }

    @RequestMapping("/updateuser/{username}/{firstname}/{lastname}/{role}/")
    Map<String, String> updateUser(@PathVariable String username, @PathVariable String firstname, @PathVariable String lastname, @PathVariable String role) {

        User newUser = new User(username, firstname, lastname, role);
        String result = _sqlServerService.updateUser(newUser);
        return Map.of("message", result);
    }
}
