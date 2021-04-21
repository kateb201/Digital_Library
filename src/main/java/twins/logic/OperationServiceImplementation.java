package twins.logic;
import twins.boundaries.*;
import twins.data.*;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
//import java.util.Optional;
//import java.util.UUID;

//import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

//import com.fasterxml.jackson.databind.ObjectMapper;
//hi
@Service
public class OperationServiceImplementation implements OperationsService{

	private OperationHandler operationHandler; 

		
	@Autowired	
	public OperationServiceImplementation(OperationHandler operationHandler) {
		super();
		this.operationHandler = operationHandler;
		//this.jackson = new ObjectMapper();
		}
	
	@Override
	public Object invokeOperation(OperationBoundary operation) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public OperationBoundary invokeAsynchronous(OperationBoundary operation) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<OperationBoundary> getAllOperations(String adminSpace, String adminEmail) {
		Iterable<OperationEntity> allOperations = this.operationHandler.findAll();
		List<OperationBoundary> lst = new ArrayList<>();
		
		for(OperationEntity operation :allOperations) {
			OperationBoundary boundary = this.convertToBoundary(operation);
			lst.add(boundary);
		}
		return lst;
	}


	@Override
	public void deleteAllOperations(String adminSpace, String adminEmail) {
		// TODO Auto-generated method stub
		
	}
	
	private OperationBoundary convertToBoundary(OperationEntity operation) {
		OperationBoundary boundary = new OperationBoundary();
		boundary.setOperationId(operation.getOperationId());
		boundary.setItem(operation.getItem());
		boundary.setCreatedTimestamp(operation.getCreatedTimestamp());
		boundary.setInvokedBy(operation.getInvokedBy());
		boundary.setItemAttributes(operation.getItemAttributes());
		boundary.setType(operation.getType());
		return boundary;
	}

}

