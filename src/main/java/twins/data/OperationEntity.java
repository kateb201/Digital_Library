package twins.data;

import java.util.Date;
import java.util.HashMap;
import javax.persistence.*;
import twins.boundaries.*;
/*
 * OPERATIONS_TABLE
 * operationId <PK>| Space | Type  
 * VARCHAR(255) | VARCHAR(255)
 *  */
@Entity
@Table(name = "OPERATIONS_TABLE")
public class OperationEntity {
	private operationId operationId;
    private String type;
    private item item;
    private Date createdTimestamp;
    private invokedBy invokedBy;
    private HashMap<String, Object> itemAttributes;
    
    public OperationEntity() {
    }
    
    @Id
    public int getId() {
		return operationId.getId();
	}
	public void setId(int id) {
		operationId.setId(id);
	}
	public String getSpace() {
		return operationId.getSpace();
	}
	public void setSpace(String space) {
		operationId.setSpace(space);
	}
	@Transient
	public operationId getOperationId() {
		return operationId;
	}
	@Transient
	public void setOperationId(operationId operationId) {
		this.operationId = operationId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Transient
	public item getItem() {
		return item;
	}
	@Transient
	public void setItem(item item) {
		this.item = item;
	}
	@Transient
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	@Transient
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	@Transient
	public invokedBy getInvokedBy() {
		return invokedBy;
	}
	@Transient
	public void setInvokedBy(invokedBy invokedBy) {
		this.invokedBy = invokedBy;
	}
	@Transient
	public HashMap<String, Object> getItemAttributes() {
		return itemAttributes;
	}
	@Transient
	public void setItemAttributes(HashMap<String, Object> itemAttributes) {
		this.itemAttributes = itemAttributes;
	}
}
