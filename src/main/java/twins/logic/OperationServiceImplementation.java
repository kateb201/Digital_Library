package twins.logic;
import twins.boundaries.*;
import twins.data.*;

import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class OperationServiceImplementation implements ExtendedOperationsService {

	private OperationHandler operationHandler;
	private UserHandler userHandler;
	private ItemHandler itemHandler;
	private OperationEntity operationInvoked;
	private ObjectMapper jackson;
		
	@Autowired	
	public OperationServiceImplementation(OperationHandler operationHandler, UserHandler userHandler, 
			ItemHandler itemHandler) {
		super();
		this.operationHandler = operationHandler;
		this.userHandler = userHandler;
		this.itemHandler = itemHandler;
		this.setOperationInvoked(null);
		this.jackson = new ObjectMapper();	
	}
	
	@Override
	@Transactional
	public Object invokeOperation(OperationBoundary operation) {
	 	Optional<UserEntity> user = userHandler.findById(operation.getInvokedBy().getUserId().getEmail());
		if (user.isPresent() == false || user.get().getRole().equals(UserRole.PLAYER.toString()) == false) {
			throw new UncheckedIOException("User " + operation.getInvokedBy().getUserId().getEmail() + " is not premitted", null);
		}
		if (operation == null  || operation.getType() == null || operation.getType() == " ") {
			throw new RuntimeException("Operations attributes must not be null");
		}
		OperationEntity entity = this.convertToEntity(operation);
		entity.setId(UUID.randomUUID().toString());
		entity.setCreatedTimestamp(new Date());
		this.setOperationInvoked(entity);
		entity = this.operationHandler.save(entity);
		switch (operation.getType()) {
		case "searchBySubject":
			String subject = (String) operation.getOperationAttributes().get("subject");
			Integer size = (Integer) operation.getOperationAttributes().get("size");
			Integer page = (Integer) operation.getOperationAttributes().get("page");
			if(size == null) {
				size = 10;
			}
			if (page == null) {
				page = 0;
			}
			if (subject == null) {
				throw new RuntimeException("Operations attributes must contain subject");
			}
			List<ItemBoundry> resultBySub = searchBySubject(subject, size, page);
			return resultBySub;
		case "searchByName":
			String name = (String)operation.getOperationAttributes().get("name");
			Integer size2 = (Integer) operation.getOperationAttributes().get("size");
			Integer page2 = (Integer) operation.getOperationAttributes().get("page");
			if(size2 == null) {
				size = 10;
			}
			if (page2 == null) {
				page = 0;
			}
			if (name == null) {
				throw new RuntimeException("Operations attributes must contain name");
			}
			List<ItemBoundry> resultByName = searchByName(name, size2, page2);
			return resultByName;
		default:
			return this.convertToBoundary(entity);
		}
	}
	
	@Override
	public List<ItemBoundry> searchByName(String name, int size, int page) {
		Iterable<ItemEntity> entities = this.itemHandler
				.findByName(name, PageRequest.of(page, size, Direction.DESC, "id"));
	    List<ItemBoundry> rv = new ArrayList<>();
	    for (ItemEntity entity : entities) {
	    	ItemBoundry boundary = this.convertToBoundaryItem(entity);
	        rv.add(boundary);
	    }
	    return rv;
	}

	public List<ItemBoundry> searchBySubject(String subject, int size, int page){
		Iterable<ItemEntity> entities = this.itemHandler.findAll();
		List<ItemBoundry> rv = new ArrayList<>();
		List<ItemBoundry> resultBySub = new ArrayList<>();
	    for (ItemEntity entity : entities) {
	    	ItemBoundry boundary = this.convertToBoundaryItem(entity);
	        rv.add(boundary);
	    }
	    for (ItemBoundry boundary : rv) {
	    	if(boundary.getItemAttributes().containsValue(subject))
	    		resultBySub.add(boundary);
	    }
		return resultBySub;
	}
	
	@Override
	@Transactional 
	public OperationBoundary invokeAsynchronous(OperationBoundary operation) { // SAME AS  INVOKE OPERATION????
		Optional<UserEntity> user = userHandler.findById(operation.getInvokedBy().getUserId().getEmail());
		if (user.isPresent() == false || user.get().getRole().equals(UserRole.PLAYER.toString()) == false) {
			throw new UncheckedIOException("User " + operation.getInvokedBy().getUserId().getEmail() + " is not premitted", null);
		}
		if (operation == null  || operation.getType() == null || operation.getType() != " ") {
			throw new RuntimeException("Operations attributes must not be null");
		}
		operation.setInvokedBy(operation.getOperationId().getSpace(), operation.getOperationId().getId());
		operation.setInvokedBy(operation.getOperationId().getSpace(), operation.getOperationId().getId());
		OperationEntity entity = this.convertToEntity(operation);
        if(entity.getItem().isActive()==false) throw new UncheckedIOException("item is not active", null);
		entity.setId(UUID.randomUUID().toString());
		entity.setCreatedTimestamp(new Date());
		this.setOperationInvoked(entity);
		if(entity !=null)
			entity = this.operationHandler.save(entity);
		return this.convertToBoundary(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<OperationBoundary> getAllOperations(String adminSpace, String adminEmail) {
		Optional<UserEntity> user = userHandler.findById(adminEmail);
		if (user.isPresent() == false || user.get().getRole().equals(UserRole.ADMIN.toString()) == false) {
			throw new UncheckedIOException("User " + adminEmail + " is not premitted", null);
		}
		Iterable<OperationEntity> allOperations = this.operationHandler.findAll();
		List<OperationBoundary> lst = new ArrayList<>();
		
		for(OperationEntity operation :allOperations) {
			OperationBoundary boundary = this.convertToBoundary(operation);			
			boundary.setInvokedBy(adminSpace, adminEmail);
			lst.add(boundary);
		}
		return lst;
	}

	@Override
	@Transactional
	public void deleteAllOperations(String adminSpace, String adminEmail)  {
		Optional<UserEntity> user = userHandler.findById(adminEmail);
		if (user.isPresent() == false || user.get().getRole().equals(UserRole.ADMIN.toString()) == false) {
			throw new UncheckedIOException("User " + adminEmail + " is not premitted", null);
		}
		this.operationHandler.deleteAll();
		
	}
	
	private OperationBoundary convertToBoundary(OperationEntity operation) {
		OperationBoundary boundary = new OperationBoundary();
		if(operation.getId() != null)
			boundary.setOperationId(new OperationId(operation.getSpace(), operation.getId()));
		boundary.setItem(operation.getItem());
		boundary.setCreatedTimestamp(operation.getCreatedTimestamp());
		boundary.setInvokedBy(operation.getSpace(), operation.getEmail());
		boundary.setOperationAttributes(this.unmarshall(operation.getOperationAttributes(), HashMap.class));
		boundary.setType(operation.getType());
		return boundary;
	}
	
    private ItemBoundry convertToBoundaryItem(ItemEntity entity) {
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
	
	private OperationEntity convertToEntity(OperationBoundary boundary) {
		OperationEntity entity = new OperationEntity();
		if(boundary.getOperationId() != null) {
			entity.setId(boundary.getOperationId().getId());
			entity.setSpace(boundary.getOperationId().getSpace());
		}
		entity.setItem(boundary.getItem());
		entity.setCreatedTimestamp(boundary.getCreatedTimestamp());
		entity.setOperationAttributes(this.marshall(boundary.getOperationAttributes()));
		entity.setType(boundary.getType());
		entity.setEmail(boundary.getInvokedBy().getUserId().getEmail());
		return entity;
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

	public OperationEntity getOperationInvoked() {
		return operationInvoked;
	}

	public void setOperationInvoked(OperationEntity operationInvoked) {
		this.operationInvoked = operationInvoked;
	}

	public ItemHandler getItemHandler() {
		return itemHandler;
	}

	public void setItemHandler(ItemHandler itemHandler) {
		this.itemHandler = itemHandler;
	}


}

