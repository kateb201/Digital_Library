package twins.logic;

import twins.boundaries.Books;
import twins.boundaries.ItemBoundry;

import java.util.List;
import java.util.Map;

public interface ExtendedItemService extends ItemsService{

    public Books searchBook(Map<String, Object> details);

	public List<ItemBoundry> getAllItemsByTheUserSpace(String userSpace, String userEmail, int size, int page);

	void doSomethingSepcificWithMessage(String id, String newValue);

}
