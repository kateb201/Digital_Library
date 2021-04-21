package twins.boundaries;

import java.util.Date;
import java.util.HashMap;

public class OperationBoundary {
	private operationId operationId;
    private String type;
    private item item;
    private Date  createdTimestamp;
    private invokedBy invokedBy;
    private HashMap<String, Object> itemAttributes;
	
    
   
    public OperationBoundary() {
        this.createdTimestamp = new Date();
        this.itemAttributes = new HashMap<>();
    }
    
    public OperationBoundary(operationId operationId, String type, twins.boundaries.item item, Date createdTimestamp,
			invokedBy invokedBy, HashMap<String, Object> itemAttributes) {
		this();
		this.operationId = operationId;
		this.type = type;
		this.item = item;
		this.createdTimestamp = createdTimestamp;
		this.invokedBy = invokedBy;
		this.itemAttributes = itemAttributes;
	}


	public operationId getOperationId() {
		return operationId;
	}


	public void setOperationId(operationId operationId) {
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


	public invokedBy getInvokedBy() {
		return invokedBy;
	}


	public void setInvokedBy(invokedBy invokedBy) {
		this.invokedBy = invokedBy;
	}


	public HashMap<String, Object> getItemAttributes() {
		return itemAttributes;
	}


	public void setItemAttributes(HashMap<String, Object> itemAttributes) {
		this.itemAttributes = itemAttributes;
	}
    
    
    
}
