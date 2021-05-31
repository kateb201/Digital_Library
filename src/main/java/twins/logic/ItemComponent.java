package twins.logic;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import twins.data.ItemEntity;
import twins.data.ItemHandler;

@Component
public class ItemComponent {
	private ItemHandler itemHandler;

	@Autowired
	public ItemComponent(ItemHandler itemHandler) {
		super();
		this.itemHandler = itemHandler;
	}
	
	@Transactional//(readOnly = false)
	public void changeContent(String id, String newValue) {
		Optional<ItemEntity> messageOptional = this.itemHandler
			.findById(id);
		
		if (messageOptional.isPresent()) {
			ItemEntity entity = messageOptional.get();
			entity.setType(newValue);
			// update the database with the entity new values
			this.itemHandler.save(entity);
		}else {
			// do nothing 
		}
	}
}
