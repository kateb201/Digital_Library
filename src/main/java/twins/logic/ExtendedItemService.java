package twins.logic;

import twins.boundaries.Books;

import java.util.Map;

public interface ExtendedItemService extends ItemsService{

    public Books searchBook(Map<String, Object> details);

}
