package twins.data;
import javax.persistence.*;

import org.springframework.beans.factory.annotation.Value;

import twins.boundaries.*;
import java.util.Date;
//import java.util.HashMap;

/*
 * OPERATIONS_TABLE
 * Id <PK>| Space | Type  | MESSAGE_TIMESTAMP | Item Attributes
 * VARCHAR(255) | VARCHAR(255) | VARCHAR(255) | TIMESTAMP | CLOB
 *  */

@Entity
@Table(name = "OPERATIONS_TABLE")
public class OperationEntity {
	private String id;
	private String space;
    private String type;
    private ItemBoundry item;
    private Date createdTimestamp;
    private InvokedBy invokedBy;
    private String itemAttributes;
    
    public OperationEntity() {
    }
    
    @Id
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSpace() {
		return space;
	}
	@Value("${spring.application.name:2021b.twins}")
	public void setSpace(String space) {
		this.space = space;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Transient
	public ItemBoundry getItem() {
		return item;
	}
	@Transient
	public void setItem(ItemBoundry item) {
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
