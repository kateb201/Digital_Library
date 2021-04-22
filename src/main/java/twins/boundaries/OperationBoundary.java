package twins.boundaries;

import java.util.Date;
import java.util.HashMap;

public class OperationBoundary {
	private OperationId operationId;
    private String type;
    private item item;
    private Date  createdTimestamp;
    private InvokedBy invokedBy;
    private HashMap<String, Object> itemAttributes;
	
    
   
    public OperationBoundary() {
        this.createdTimestamp = new Date();
        this.itemAttributes = new HashMap<>();
    }
    
    public OperationBoundary(OperationId operationId, String type, twins.boundaries.item item, Date createdTimestamp,
							 InvokedBy invokedBy, HashMap<String, Object> itemAttributes) {
		this();
		this.operationId = operationId;
		this.type = type;
		this.item = item;
		this.createdTimestamp = createdTimestamp;
		this.invokedBy = invokedBy;
		this.itemAttributes = itemAttributes;
	}


	public OperationId getOperationId() {
		return operationId;
	}


	public void setOperationId(OperationId operationId) {
		this.operationId = operationId;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public item getItem() {
		return item;
	}


	public void setItem(item item) {
		this.item = item;
	}


	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}


	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}


	public InvokedBy getInvokedBy() {
		return invokedBy;
	}


	public void setInvokedBy(InvokedBy invokedBy) {
		this.invokedBy = invokedBy;
	}


	public HashMap<String, Object> getItemAttributes() {
		return itemAttributes;
	}


	public void setItemAttributes(HashMap<String, Object> itemAttributes) {
		this.itemAttributes = itemAttributes;
	}
    
    
    
}
