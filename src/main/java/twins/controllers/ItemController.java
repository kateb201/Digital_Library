package twins.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import twins.boundaries.ItemBoundry;
import twins.boundaries.ItemId;
import twins.boundaries.CreatedBy;
import twins.boundaries.Location;
import twins.logic.ItemsService;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;

@RestController
public class ItemController {

    private ArrayList<ItemBoundry> items = new ArrayList<>();
    private ItemsService itemService;
    
    @Autowired
	public void setItemsService(ItemsService itemService) {
		this.itemService = itemService;
	}

    @RequestMapping(path = "/twins/items/{userSpace}/{userEmail}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemBoundry createItem(@PathVariable("userSpace") String userSpace,
                                  @PathVariable("userEmail") String userEmail,
                                  @RequestBody ItemBoundry input) {
        //create item
    	return this.itemService.createItem(userSpace, userEmail, input);
    	
    }

    @RequestMapping(path = "/twins/items/{userSpace}/{userEmail}/{itemSpace}/{itemId}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateItem(@PathVariable("userSpace") String userSpace,
                           @PathVariable("userEmail") String userEmail,
                           @PathVariable("itemSpace") String itemSpace,
                           @PathVariable("itemId") String id,
                           @RequestBody ItemBoundry input) {
        this.itemService.updateItem(userSpace, userEmail, itemSpace, id, input);
       
    }

    @RequestMapping(path = "/twins/items/{userSpace}/{userEmail}/{itemSpace}/{itemId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemBoundry getItem(@PathVariable("userSpace") String userSpace,
                               @PathVariable("userEmail") String userEmail,
                               @PathVariable("itemSpace") String itemSpace,
                               @PathVariable("itemId") String id) {
        //get Specific Item
        
        return this.itemService.getSpecificItem(userSpace, userEmail, itemSpace, id);
    }

    @RequestMapping(path = "/twins/items/{userSpace}/{userEmail}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemBoundry> getAllItem(@PathVariable("userSpace") String userSpace,
                                             @PathVariable("userEmail") String userEmail) {
        //get all Items
    	List<ItemBoundry> allItems = this.itemService.getAllItems(userSpace, userEmail);
        return allItems;
    }

}
