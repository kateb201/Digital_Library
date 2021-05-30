package twins.logic;

import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import twins.boundaries.Books;
import twins.BooksAPI;
import twins.boundaries.*;
import twins.data.ItemEntity;
import twins.data.ItemHandler;
import twins.data.UserEntity;
import twins.data.UserHandler;
import twins.data.UserRole;

@Service
public class ItemLogicImplementation implements ExtendedItemService {
    private ItemHandler itemHandler;
    private UserHandler userHandler;
    private ObjectMapper jackson;
    
    

    @Autowired
    public ItemLogicImplementation(ItemHandler itemHandler, UserHandler userHandler) {
        super();
        this.itemHandler = itemHandler;
        this.userHandler = userHandler;
        this.jackson = new ObjectMapper();

    }

    @Override
    @Transactional
    public ItemBoundry createItem(String userSpace, String userEmail, ItemBoundry item) {
    	Optional<UserEntity> user = userHandler.findById(userEmail);
		if (!user.isPresent() || user.get().getRole() != UserRole.MANAGER.toString()) {
			throw new UncheckedIOException("User " + userEmail + " is not premitted", null);
		}
    	
        if (item.getType() == null || item.getType() == " ") {
            throw new RuntimeException("type attribute must not be null");
        }
        if (item.getName() == null || item.getType() == " ") {
            throw new RuntimeException("name attribute must not be null");
        }
        //Books fromAPI = searchBook(item.getItemAttributes());
        //for (int i = 0; i < Integer.parseInt(BooksAPI.MAX_RESULTS); i++) {
        //    ItemBoundry new_item = (ItemBoundry) item.clone();
        //    new_item = insertVolumeInfoToItemAttr(new_item, fromAPI.getItems(), i); //set result from api to item attr.
        //    entity = this.convertToEntity(new_item);
        //    entity.setId(UUID.randomUUID().toString());
        //    entity.setCreatedTimestamp(new Date());
        //    entity.setSpace(userSpace);
        //    entity.setEmail(userEmail);
        //    entity = this.itemHandler.save(entity);
        //}
        ItemEntity entity = this.convertToEntity(item);
        entity.setId(UUID.randomUUID().toString());
        entity.setCreatedTimestamp(new Date());
        entity.setSpace(userSpace);
        entity.setEmail(userEmail);
        entity = this.itemHandler.save(entity);
        return this.convertToBoundary(entity);

    }

    private ItemBoundry insertVolumeInfoToItemAttr(ItemBoundry item, Items[] volumeInfo, int index) {
        Map<String, Object> itemAttr = unmarshall(marshall(volumeInfo[index]), Map.class);
        item.setItemAttributes(itemAttr);
        item.setName((String) itemAttr.get("Title"));
        return item;
    }

    @Override
    @Transactional
    public ItemBoundry updateItem(String userSpace, String userEmail, String itemSpace, String itemId, ItemBoundry update) {
    	Optional<UserEntity> user = userHandler.findById(userEmail);
		if (!user.isPresent() || user.get().getRole() != UserRole.MANAGER.toString()) {
			throw new UncheckedIOException("User " + userEmail + " is not premitted", null);
		}
    	Optional<ItemEntity> existing = this.itemHandler.findById(itemId);
        if (existing.isPresent()) {
            update.setItemId(new ItemId(itemSpace, itemId));
            update.setCreatedBy(new CreatedBy(userSpace, userEmail));
            update = updateVolumeDetails(update, existing);
            ItemEntity updatedEntity = this.convertToEntity(update);
            updatedEntity.setCreatedTimestamp(existing.get().getCreatedTimestamp());

            // UPDATE
            this.itemHandler.save(updatedEntity);
        } else {
            throw new RuntimeException("message could not be found");
        }
        return update;
    }

    private ItemBoundry updateVolumeDetails(ItemBoundry update, Optional<ItemEntity> existing) {
        int updateVal = 0;
        for (Map.Entry<String, Object> entry : update.getItemAttributes().entrySet()) {
            if (entry.getKey().equals("Price")) {
                updateVal = (int) entry.getValue();
                update.setItemAttributes(convertToBoundary(existing.get()).getItemAttributes());
                break;
            }
        }
        update.getItemAttributes().put("Price", updateVal);
        return update;
    }

    @Override
    @Transactional(readOnly = true) // handle race condition
    public List<ItemBoundry> getAllItems(String userSpace, String userEmail) {
    	Optional<UserEntity> user = userHandler.findById(userEmail);
		if (!user.isPresent() || 
				(user.get().getRole() != UserRole.MANAGER.toString()
				  && user.get().getRole() != UserRole.PLAYER.toString())) {
			throw new UncheckedIOException("User " + userEmail + " is not premitted", null);
		}

        Iterable<ItemEntity> allEntities = this.itemHandler.findAll();
        List<ItemBoundry> rv = new ArrayList<>();
        for (ItemEntity entity : allEntities) {
        	if (entity.isActive() == true) {
        		 ItemBoundry boundary = this.convertToBoundary(entity);
                 rv.add(boundary);        	}
        }
        return rv;
    }


    @Override
    @Transactional(readOnly = true)
    public ItemBoundry getSpecificItem(String userSpace, String userEmail, String itemSpace, String itemId) {
    	Optional<UserEntity> user = userHandler.findById(userEmail);
		if (!user.isPresent() || 
				(user.get().getRole() != UserRole.MANAGER.toString()
				  && user.get().getRole() != UserRole.PLAYER.toString())) {
			throw new UncheckedIOException("User " + userEmail + " is not premitted", null);
		}
        Optional<ItemEntity> existing = this.itemHandler.findById(itemId);
        if(existing.get().isActive()==false) throw new UncheckedIOException("item is not active", null);
        if (existing.isPresent() ) {
            ItemEntity entity = existing.get();
            return this.convertToBoundary(entity);
        } else {
            throw new RuntimeException("message could not be found");
        }
    }

    @Override
    @Transactional
    public void deleteAllItems(String adminSpace, String adminEmail) {
        this.itemHandler.deleteAll();

    }

    private ItemEntity convertToEntity(ItemBoundry boundary) {
        ItemEntity entity = new ItemEntity();
        entity.setCreatedTimestamp(boundary.getCreatedTimestamp());
        entity.setActive(boundary.isActive());
        entity.setName(boundary.getName());
        entity.setType(boundary.getType());

        // marshalling of Map to JSON (returned as String)
        entity.setItemAttributes(this.marshall(boundary.getItemAttributes()));

        if (boundary.getItemId() != null) {
            entity.setId(boundary.getItemId().getId());
            entity.setSpace(boundary.getItemId().getSpace());
        }
        if (boundary.getLocation() != null) {
            entity.setLat(boundary.getLocation().getLat());
            entity.setLng(boundary.getLocation().getLng());
        }
        if (boundary.getCreatedBy() != null) {
            entity.setEmail(boundary.getCreatedBy().getUserId().getEmail());
        }
        return entity;
    }


    private ItemBoundry convertToBoundary(ItemEntity entity) {
        ItemBoundry boundary = new ItemBoundry();
        boundary.setItemId(new ItemId(entity.getSpace(), entity.getId()));
        boundary.setType(entity.getType());
        boundary.setName(entity.getName());
        boundary.setActive(entity.isActive());
        boundary.setCreatedTimestamp(entity.getCreatedTimestamp());
        boundary.setCreatedBy(new CreatedBy(entity.getSpace(), entity.getEmail()));
        boundary.setLocation(new Location(entity.getLat(), entity.getLng()));

        String attributes = entity.getItemAttributes();
        // use jackson for unmarshalling JSON --> Map
        Map<String, Object> itemAttributesMap = this.unmarshall(attributes, Map.class);
        boundary.setItemAttributes(itemAttributesMap);
        return boundary;
    }

    public String marshall(Object value) {
        try {
            return this.jackson
                    .writeValueAsString(value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T unmarshall(String json, Class<T> requiredType) {
        try {
            return this.jackson
                    .readValue(json, requiredType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Books searchBook(Map<String, Object> details) {
        return BooksAPI.searchByTitle(details);//.block();
    }
}
