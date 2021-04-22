package twins.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import twins.boundaries.UserBoundary;
import twins.logic.UsersService;


@RestController
public class UserController {

    private UsersService userService;

    @Autowired
    public UserController(UsersService userService) {
        this.userService = userService;
    }

    @RequestMapping(
            path = "/twins/users",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserBoundary createNewUser(@RequestBody UserBoundary input) {
        return input;

    }

    @RequestMapping(path = "/twins/users/login/{userSpace}/{userEmail}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserBoundary login(
            @PathVariable("userSpace") String space,
            @PathVariable("userEmail") String email) {
        return new UserBoundary();
    }

    @RequestMapping(
            path = "/twins/users/{userSpace}/{userEmail}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUser(
            @PathVariable("userSpace") String space, @PathVariable("userEmail") String email,
            @RequestBody UserBoundary update) {
        System.err.println(" update: " + update);
    }

}