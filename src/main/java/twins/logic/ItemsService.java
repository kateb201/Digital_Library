package twins.logic;

import java.util.List;
import twins.boundaries.*;

public interface ItemsService {
    public ItemBoundry createItem(String userSpace, String userEmail, ItemBoundry item);

    public ItemBoundry updateItem(String userSpace, String userEmail, String itemSpace, String itemId, ItemBoundry update);

    public List<ItemBoundry> getAllItems(String userSpace, String userEmail);

    public ItemBoundry getSpecificItem(String userSpace, String userEmail, String itemSpace, String itemId);

    public void deleteAllItems(String adminSpace, String adminEmail);

}
