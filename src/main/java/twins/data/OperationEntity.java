package twins.data;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import twins.boundaries.*;
import java.util.Date;
//import java.util.HashMap;

/*
 * OPERATIONS_TABLE
 * Id <PK>| Space | Type  | MESSAGE_TIMESTAMP | Item Attributes
 * VARCHAR(255) | VARCHAR(255) | VARCHAR(255) | TIMESTAMP | CLOB
 *  */


@Document(collection = "Operation")
public class OperationEntity {
	@Id
	private String id;
	private String space;
	private String email;
    private String type;
    private ItemBoundry item;
    private Date createdTimestamp;
    private String itemAttributes;
    
    public OperationEntity() {
    }
    
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

	public ItemBoundry getItem() {
		return item;
	}

	public void setItem(ItemBoundry item) {
		this.item = item;
	}

	@Field(name="MESSAGE_TIMESTAMP") //  set column name: MESSAGE_TIMESTAMP
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public String getItemAttributes() {
		return itemAttributes;
	}
	
	public void setItemAttributes(String itemAttributes) {
		this.itemAttributes = itemAttributes;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
