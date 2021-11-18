package io.recruitment.assessment.api.controllers;

import model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.SqlServerServiceImpl;

import java.util.Map;

@RestController
public class UserController {

    private SqlServerServiceImpl _sqlServerService;

    public UserController(SqlServerServiceImpl sqlServerService){
        _sqlServerService = sqlServerService;
    }
    @RequestMapping("/adduser/{username}/{firstname}/{lastname}/{role}")
    Boolean addUser(@PathVariable String username, @PathVariable String firstname, @PathVariable String lastname, @PathVariable String role) {
        User newUser = new User(username, firstname,lastname,role);
        return _sqlServerService.addUser(newUser);
    }

}
