package twins.controllers;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import twins.boundaries.UserBoundary;
import twins.boundaries.NewUserDetails;
import twins.boundaries.UserIdBoundary;


@RestController
public class UserController {
	
	@RequestMapping(
			path="/twins/users",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
		public NewUserDetails storeMessage (@RequestBody NewUserDetails input){
//			input.setCurrentTimestamp(new Date());
			// STUB
		 // items.add(input);
			return input;
			
	}
	
	@RequestMapping(path = "/twins/users/login/{userSpace}/{userEmail}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary createUser(
			@PathVariable("userSpace") String space,
			@PathVariable("userEmail") String email) {
		UserBoundary rv = new UserBoundary();
		rv.setUserID(new UserIdBoundary(space, email));
		return rv;
	}
	
	@RequestMapping(
			path="/twins/users/{userSpace}/{userEmail}",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateUser (
			@PathVariable("userSpace") String space,@PathVariable("userEmail") String email, 
			@RequestBody UserBoundary update){
		// STUB implementation
		System.err.println(" update: " + update);
	}
	
	}	

	/*
	
	 @RequestMapping(path = "/twins/users/login/{userSpace}/{userEmail}",
	            method = RequestMethod.GET,
	            consumes = MediaType.APPLICATION_JSON_VALUE,
	            produces = MediaType.APPLICATION_JSON_VALUE)
	    public UserBoundary  createItem(@PathVariable("userSpace") String userSpace, @PathVariable("userEmail") String userEmail) { 
  	UserBoundary rv = new UserBoundary();
		rv.setUserID(new userID(userSpace, userEmail));
		return rv;

		
		
		
		
		
	 }    	*/