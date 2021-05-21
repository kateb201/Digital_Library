package twins.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import twins.boundaries.Books;
import twins.boundaries.ItemBoundry;
import twins.logic.ItemsService;

import java.util.List;

@RestController
public class ItemController {

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

    //TODO merge into defined method
    @RequestMapping(path = "api/{title}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Books searchItem(@PathVariable("title") String title) {
        //search in Google Books API
        return this.itemService.searchBook(title);
    }

}
