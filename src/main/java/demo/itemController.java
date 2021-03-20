package demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class itemController {

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
        return input;
    }
}
