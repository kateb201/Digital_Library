package twins.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import twins.boundaries.ItemBoundry;
import twins.boundaries.ItemId;
import twins.boundaries.createdBy;
import twins.boundaries.location;

import java.util.ArrayList;
import java.util.stream.IntStream;

@RestController
public class itemController {

    private ArrayList<ItemBoundry> items = new ArrayList<>();

    @RequestMapping(path = "/twins/items/{userSpace}/{userEmail}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemBoundry createItem(@PathVariable("userSpace") String userSpace,
                                  @PathVariable("userEmail") String userEmail,
                                  @RequestBody ItemBoundry input) {
        //STUB implementation
        input.setCreatedBy(new createdBy(userSpace, userEmail));
        input.setLocation(new location());
        items.add(input);
        return input;
    }

    @RequestMapping(path = "/twins/items/{userSpace}/{userEmail}/{itemSpace}/{itemId}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateItem(@PathVariable("userSpace") String userSpace,
                           @PathVariable("userEmail") String userEmail,
                           @PathVariable("itemSpace") String itemSpace,
                           @PathVariable("itemId") int id,
                           @RequestBody ItemBoundry input) {
        //STUB implementation
        int index = IntStream.range(0, items.size()).filter(i -> items.get(i).getItemId().getId() == id).findAny().orElse(-1);
        if (index != -1) {
            items.set(index, input);
        }
    }

    @RequestMapping(path = "/twins/items/{userSpace}/{userEmail}/{itemSpace}/{itemId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemBoundry getItem(@PathVariable("userSpace") String userSpace,
                               @PathVariable("userEmail") String userEmail,
                               @PathVariable("itemSpace") String itemSpace,
                               @PathVariable("itemId") int id) {
        //STUB implementation
        ItemBoundry item = items.stream().filter(i -> i.getItemId().getId() == id).findAny().orElse(null);
        if (item == null) {
            return new ItemBoundry(new ItemId(userSpace, id), "", true, new createdBy(userSpace, userEmail));
        }
        return item;
    }

    @RequestMapping(path = "/twins/items/{userSpace}/{userEmail}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<ItemBoundry> getAllItem(@PathVariable("userSpace") String userSpace,
                                             @PathVariable("userEmail") String userEmail) {
        //STUB implementation
        return items;
    }

}
