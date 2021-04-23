package twins.data;
import javax.persistence.*;
import twins.boundaries.*;
import java.util.Date;
import java.util.HashMap;

/*
 * OPERATIONS_TABLE
 * operationId <PK>| Space | Type  | MESSAGE_TIMESTAMP | Item Attributes
 * VARCHAR(255) | VARCHAR(255) | VARCHAR(255) | TIMESTAMP | CLOB
 *  */

@Entity
@Table(name = "OPERATIONS_TABLE")
public class OperationEntity {
	private OperationId operationId;
    private String type;
    private item item;
    private Date createdTimestamp;
    private InvokedBy invokedBy;
    private String itemAttributes;
    
    public OperationEntity() {
    }
    
    @Id
    public String getId() {
		return operationId.getId();
	}
	public void setId(String id) {
		operationId.setId(id);
	}
	public String getSpace() {
		return operationId.getSpace();
	}
	public void setSpace(String space) {
		operationId.setSpace(space);
	}
	@Transient
	public OperationId getOperationId() {
		return operationId;
	}
	@Transient
	public void setOperationId(OperationId operationId) {
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
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="MESSAGE_TIMESTAMP") //  set column name: MESSAGE_TIMESTAMP
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	@Transient
	public InvokedBy getInvokedBy() {
		return invokedBy;
	}
	@Transient
	public void setInvokedBy(InvokedBy invokedBy) {
		this.invokedBy = invokedBy;
	}
	@Lob
	public String getItemAttributes() {
		return itemAttributes;
	}
	
	public void setItemAttributes(String itemAttributes) {
		this.itemAttributes = itemAttributes;
	}
}
