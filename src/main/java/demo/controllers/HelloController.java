package demo.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.Helper;
import demo.boundaries.HelloBoundary;

@RestController
public class HelloController {

    @RequestMapping(
            path="/hello",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public HelloBoundary hello(){
        Helper help = new Helper();
        return new HelloBoundary("Hello ! " + help.getTeamName());
    }
}
