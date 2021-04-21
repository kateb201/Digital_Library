package twins.logic;
import org.springframework.data.repository.CrudRepository;

import twins.data.ItemEntity;

public interface ItemHandler extends CrudRepository<ItemEntity, String>{

}
