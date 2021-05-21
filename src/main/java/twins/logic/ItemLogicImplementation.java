package twins.logic;

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

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import twins.Books;
import twins.BooksAPI;
import twins.boundaries.*;
import twins.data.ItemEntity;

@Service
public class ItemLogicImplementation implements ItemsService {
    private ItemHandler itemHandler;
    private ObjectMapper jackson;

    @Autowired
    public ItemLogicImplementation(ItemHandler itemHandler) {
        super();
        this.itemHandler = itemHandler;
        this.jackson = new ObjectMapper();

    }

    @Override
    @Transactional
    public ItemBoundry createItem(String userSpace, String userEmail, ItemBoundry item) {
        if (item.getType() == null) {
            throw new RuntimeException("type attribute must not be null");
        }
        if (item.getName() == null) {
            throw new RuntimeException("name attribute must not be null");
        }

        ItemEntity entity = this.convertToEntity(item);

        entity.setId(UUID.randomUUID().toString());
        entity.setCreatedTimestamp(new Date());
        entity.setSpace(userSpace);
        entity.setEmail(userEmail);

        entity = this.itemHandler.save(entity);

        return this.convertToBoundary(entity);

    }

    @Override
    @Transactional
    public ItemBoundry updateItem(String userSpace, String userEmail, String itemSpace, String itemId, ItemBoundry update) {
        Optional<ItemEntity> existing = this.itemHandler.findById(itemId);
        if (existing.isPresent()) {
            update.setItemId(new ItemId(itemSpace, itemId));
            update.setCreatedBy(new CreatedBy(userSpace, userEmail));
            ItemEntity updatedEntity = this.convertToEntity(update);
            updatedEntity.setCreatedTimestamp(existing.get().getCreatedTimestamp());

            // UPDATE
            this.itemHandler.save(updatedEntity);
        } else {
            throw new RuntimeException("message could not be found");
        }
        return update;
    }

    @Override
    @Transactional(readOnly = true) // handle race condition
    public List<ItemBoundry> getAllItems(String userSpace, String userEmail) {
        Iterable<ItemEntity> allEntities = this.itemHandler.findAll();

        List<ItemBoundry> rv = new ArrayList<>();
        for (ItemEntity entity : allEntities) {
            ItemBoundry boundary = this.convertToBoundary(entity);
            rv.add(boundary);
        }
        return rv;
    }


    @Override
    @Transactional(readOnly = true)
    public ItemBoundry getSpecificItem(String userSpace, String userEmail, String itemSpace, String itemId) {
        Optional<ItemEntity> existing = this.itemHandler.findById(itemId);
        if (existing.isPresent()) {
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

    private String marshall(Object value) {
        try {
            return this.jackson
                    .writeValueAsString(value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T unmarshall(String json, Class<T> requiredType) {
        try {
            return this.jackson
                    .readValue(json, requiredType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Books searchBook(String title) {
        Mono<Books> res = BooksAPI.searchByTitle(title);
        return res.block();
    }

}
