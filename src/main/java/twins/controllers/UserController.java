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
        return userService.createUser(input);

    }

    @RequestMapping(path = "/twins/users/login/{userSpace}/{userEmail}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserBoundary login(
            @PathVariable("userSpace") String space,
            @PathVariable("userEmail") String email) {
        return userService.login(space, email);
    }

    @RequestMapping(
            path = "/twins/users/{userSpace}/{userEmail}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserBoundary updateUser(
            @PathVariable("userSpace") String space, @PathVariable("userEmail") String email,
            @RequestBody UserBoundary update) {
        return userService.updateUser(space, email, update);
    }

    @RequestMapping(
            path = "/twins/users/{adminSpace}/{adminEmail}",
            method = RequestMethod.DELETE)
    public void deleteAllUsers(@PathVariable("adminSpace") String space, @PathVariable("adminEmail") String email) {
        userService.deleteAllUsers(space, email);
    }

}