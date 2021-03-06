package twins.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import twins.boundaries.ItemBoundry;
import twins.logic.ExtendedItemService;

@RestController
public class ItemController {

    private ExtendedItemService itemService;

    @Autowired
    public void setItemsService(ExtendedItemService itemService) {
        this.itemService = itemService;
    }

    //ADMIN only
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
    public ItemBoundry[] getAllItem(@PathVariable("userSpace") String userSpace,
                                        @PathVariable("userEmail") String userEmail,
                            			
        @RequestParam(name="size", required = false, defaultValue = "20") int  size,
        @RequestParam(name="page", required = false, defaultValue = "0") int page) {
        //get all Items
    	
    	List<ItemBoundry> boundaries = this.itemService
    			.getAllItemsByTheUserSpace (userSpace, userEmail, size, page);
    		
    		return boundaries
    			.toArray(new ItemBoundry[0]);
    }

}
