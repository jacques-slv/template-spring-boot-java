package io.recruitment.assessment.api.controllers;

import model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.SqlServerServiceImpl;

import java.util.List;
import java.util.Map;

//@RestController
//@RequestMapping("/hello-world")
//public class UserController {

//    private SqlServerServiceImpl _sqlServerService;
//
//    public UserController(SqlServerServiceImpl sqlServerService){
//        _sqlServerService = sqlServerService;
//    }
//
//    @RequestMapping("/adduser/{username}/{firstname}/{lastname}/{role}")
//    Boolean addUser(@PathVariable String username, @PathVariable String firstname, @PathVariable String lastname, @PathVariable String role) {
//        User newUser = new User(username, firstname,lastname,role);
//        return _sqlServerService.addUser(newUser);
//    }

//}

@RestController
public class UserController {

    private SqlServerServiceImpl _sqlServerService;

    public UserController(SqlServerServiceImpl sqlServerService){
        _sqlServerService = sqlServerService;
    }


    @RequestMapping("/adduser/{username}/{firstname}/{lastname}/{role}")
    Map<String, String> addUser(@PathVariable String username, @PathVariable String firstname, @PathVariable String lastname, @PathVariable String role) {

        User newUser = new User(username, firstname, lastname, role);
        String result =_sqlServerService.addUser(newUser);

        return Map.of("message", result);
    }

    @RequestMapping("/getuser/{username}")
    Map<String, List<User>> getUser(@PathVariable String username) {

        List<User> users = _sqlServerService.getUser(username);

        return Map.of("message", users);
    }

    @RequestMapping("/deleteuser/{username}")
    Map<String, String> deleteUser(@PathVariable String username) {

        String result =_sqlServerService.deleteUser(username);

        return Map.of("message", result);
    }

}
