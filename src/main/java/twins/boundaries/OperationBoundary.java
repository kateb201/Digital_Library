package twins.boundaries;

import java.util.Date;
import java.util.HashMap;

public class OperationBoundary {
	private OperationId operationId;
    private String type;
    private ItemBoundry item;
    private Date  createdTimestamp;
    private InvokedBy invokedBy;
    private HashMap<String, Object> itemAttributes;
	
    
   
    public OperationBoundary() {
        this.createdTimestamp = new Date();
        this.itemAttributes = new HashMap<>();
    }
    
    public OperationBoundary(OperationId operationId, String type, ItemBoundry item,
    		InvokedBy invok,
    		HashMap<String, Object> itemAttributes) {
		this();
		this.operationId = operationId;
		this.type = type;
		this.item = item;
		this.invokedBy = invok;
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


	public ItemBoundry getItem() {
		return item;
	}


	public void setItem(ItemBoundry item) {
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


	public void setInvokedBy(String space, String email) {
		this.invokedBy = new InvokedBy(space, email);
	}


	public HashMap<String, Object> getItemAttributes() {
		return itemAttributes;
	}


	public void setItemAttributes(HashMap<String, Object> itemAttributes) {
		this.itemAttributes = itemAttributes;
	}
    
    
    
}
