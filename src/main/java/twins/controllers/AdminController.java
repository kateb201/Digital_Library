package twins.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import twins.boundaries.*;
import twins.logic.*;

@RestController
public class AdminController {
	
	private ExtendedUsersService userService;
	private ItemsService itemService;
	private OperationsService operationService;
	
	@Autowired
	public AdminController(ExtendedUsersService userService, ItemsService itemService, OperationsService operationService) {
		this.userService = userService;
		this.itemService = itemService;
		this.operationService = operationService;
	}

    @RequestMapping(path = "/twins/admin/users/{userSpace}/{userEmail}",
            method = RequestMethod.DELETE)
    public void deleteAllUsers(@PathVariable("userSpace") String space, @PathVariable("userEmail") String email) {
        userService.deleteAllUsers(space, email);
    	
    }
    
    @RequestMapping(path = "/twins/admin/items/{userSpace}/{userEmail}",
            method = RequestMethod.DELETE)
    public void deleteAllItems(@PathVariable("userSpace") String space, @PathVariable("userEmail") String email) {
        itemService.deleteAllItems(space, email);
       
    }
    
    @RequestMapping(path = "/twins/admin/operations/{userSpace}/{userEmail}",
            method = RequestMethod.DELETE)
    public void deleteAllOperations(@PathVariable("userSpace") String space, @PathVariable("userEmail") String email) {
        operationService.deleteAllOperations(space, email);
    
    }
    
    @RequestMapping(path = "/twins/admin/users/{userSpace}/{userEmail}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserBoundary[] getAllUsers(@PathVariable("userSpace") String userSpace,
    									@PathVariable("userEmail") String userEmail,
    	//get all Items	
    	 @RequestParam(name="size", required = false, defaultValue = "20") int  size,
         @RequestParam(name="page", required = false, defaultValue = "0") int page) {
         //get all Items
     	
     	List<UserBoundary> boundaries = this.userService
     			.getAllUsersByTheUserSpace(userSpace, userEmail, size, page);
     		
     		return boundaries.toArray(new UserBoundary[0]);
    	/*
    	List<UserBoundary> allUsers = this.userService.getAllUsers(userSpace, userEmail);
    	return allUsers;*/
    }
    
    @RequestMapping(path = "/twins/admin/operations/{userSpace}/{userEmail}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OperationBoundary> getAllOperations(@PathVariable("userSpace") String userSpace,
            @PathVariable("userEmail") String userEmail) {
    	//get all Items
    	List<OperationBoundary> allOperations = this.operationService.getAllOperations(userSpace, userEmail);
    	return allOperations;
    
    }
}
