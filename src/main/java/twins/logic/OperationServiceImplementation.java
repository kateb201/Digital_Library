package twins.logic;
import twins.boundaries.*;
import twins.data.*;

import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.naming.NoPermissionException;

import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationServiceImplementation implements OperationsService {

	private OperationHandler operationHandler;
	private UserHandler userHandler;
	private OperationEntity operationInvoked;
	private ObjectMapper jackson;
		
	@Autowired	
	public OperationServiceImplementation(OperationHandler operationHandler, UserHandler userHandler) {
		super();
		this.operationHandler = operationHandler;
		this.userHandler = userHandler;
		this.setOperationInvoked(null);
		this.jackson = new ObjectMapper();
		
		
		}
	
	@Override
	@Transactional
	public Object invokeOperation(OperationBoundary operation) {
	 	Optional<UserEntity> user = userHandler.findById(operation.getInvokedBy().getUserId().getEmail());
		if (!user.isPresent() || 
			user.get().getRole() != UserRole.PLAYER.toString()) {
			throw new UncheckedIOException("User " + operation.getInvokedBy().getUserId().getEmail() + " is not premitted", null);
		}
		if (operation == null  || operation.getType() != null || operation.getType() != " ")
			throw new RuntimeException("Operations attributes must not be null");
		OperationEntity entity = this.convertToEntity(operation);
        if(entity.getItem().isActive()==false) throw new UncheckedIOException("item is not active", null);
		entity.setId(UUID.randomUUID().toString());
		entity.setCreatedTimestamp(new Date());
		this.setOperationInvoked(entity);
		entity = this.operationHandler.save(entity);
		return this.convertToBoundary(entity);
	}


	@Override
	@Transactional 
	public OperationBoundary invokeAsynchronous(OperationBoundary operation) { // SAME AS  INVOKE OPERATION????
		Optional<UserEntity> user = userHandler.findById(operation.getInvokedBy().getUserId().getEmail());
		if (!user.isPresent() || 
			user.get().getRole() != UserRole.PLAYER.toString()) {
			throw new UncheckedIOException("User " + operation.getInvokedBy().getUserId().getEmail() + " is not premitted", null);
		}
		if (operation == null)
			throw new RuntimeException("Operation attribute must not be null");
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
		if (!user.isPresent() || user.get().getRole() != UserRole.ADMIN.toString()) {
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
		if (!user.isPresent() || user.get().getRole() != UserRole.ADMIN.toString()) {
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
	


}

