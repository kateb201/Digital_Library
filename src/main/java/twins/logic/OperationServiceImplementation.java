package twins.logic;
import twins.boundaries.*;
import twins.data.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationServiceImplementation implements OperationsService{

	private OperationHandler operationHandler;
	private OperationEntity operationInvoked;
	private ObjectMapper jackson;
		
	@Autowired	
	public OperationServiceImplementation(OperationHandler operationHandler) {
		super();
		this.operationHandler = operationHandler;
		this.setOperationInvoked(null);
		this.jackson = new ObjectMapper();
		
		
		}
	
	@Override
	@Transactional
	public Object invokeOperation(OperationBoundary operation) {
		if (operation == null)
			throw new RuntimeException("Operation attribute must not be null");
		operation.setInvokedBy(new InvokedBy(operation.getOperationId().getSpace(), "123"));
		OperationEntity entity = this.convertToEntity(operation);
		entity.setId(UUID.randomUUID().toString());
		entity.setCreatedTimestamp(new Date());
		this.setOperationInvoked(entity);
		entity = this.operationHandler.save(entity);
		return this.convertToBoundary(entity);
	}


	@Override
	@Transactional 
	public OperationBoundary invokeAsynchronous(OperationBoundary operation) { // SAME AS  INVOKE OPERATION????
		if (operation == null)
			throw new RuntimeException("Operation attribute must not be null");
		OperationEntity entity = this.convertToEntity(operation);
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
		Iterable<OperationEntity> allOperations = this.operationHandler.findAll();
		List<OperationBoundary> lst = new ArrayList<>();
		
		for(OperationEntity operation :allOperations) {
			OperationBoundary boundary = this.convertToBoundary(operation);
			InvokedBy invok = new InvokedBy(adminSpace, adminEmail);
			boundary.setInvokedBy(invok);
			lst.add(boundary);
		}
		return lst;
	}


	@Override
	@Transactional
	public void deleteAllOperations(String adminSpace, String adminEmail) {
		this.operationHandler.deleteAll();
	}
	
	private OperationBoundary convertToBoundary(OperationEntity operation) {
		OperationBoundary boundary = new OperationBoundary();
		boundary.setOperationId(operation.getOperationId());
		boundary.setItem(operation.getItem());
		boundary.setCreatedTimestamp(operation.getCreatedTimestamp());
		boundary.setInvokedBy(operation.getInvokedBy());
		boundary.setItemAttributes(this.unmarshall(operation.getItemAttributes(), HashMap.class));
		boundary.setType(operation.getType());
		return boundary;
	}
	
	private OperationEntity convertToEntity(OperationBoundary boundary) {
		OperationEntity entity = new OperationEntity();
		entity.setOperationId(boundary.getOperationId());
		entity.setId(entity.getOperationId().getId());
		entity.setItem(boundary.getItem());
		entity.setCreatedTimestamp(boundary.getCreatedTimestamp());
		entity.setItemAttributes(this.marshall(boundary.getItemAttributes()));
		entity.setType(boundary.getType());
		entity.setInvokedBy(boundary.getInvokedBy());
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

