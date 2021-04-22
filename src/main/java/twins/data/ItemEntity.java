package twins.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name="Items_TABLE")
public class ItemEntity {
		//private ItemId itemId;
		private String id;
		private String space;
		private String email;
	    private String type = "book";
	    private String name;
	    private boolean active;
	    private Date createdTimestamp;
	    private double lat;
	    private double lng;
	    //private HashMap<String, Object> itemAttributes;
	    private String itemAttributes;
	    
	
		public ItemEntity() {
		}

		@Id
		public String getId() {
			return id;
		}
		
		public void setId(String id) {
			this.id = id;
		}
		
		@Temporal(TemporalType.TIMESTAMP)
		@Column(name="Items_TIMESTAMP") //  set column name: MESSAGE_TIMESTAMP
		public Date getCreatedTimestamp() {
			return createdTimestamp;
		}

		
		public void setCreatedTimestamp(Date createdTimestamp) {
			this.createdTimestamp = createdTimestamp;
		}


		@Lob  // configure column type to support really large JSONs
		public String getItemAttributes() {
			return itemAttributes;
		}

		public void setItemAttributes(String itemAttributes) {
			this.itemAttributes = itemAttributes;
		}

		public String getSpace() {
			return space;
		}
		@Value("${spring.application.name:2021b.twins}")
		public void setSpace(String space) {
			this.space = space;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
			if(email.matches(regex)) {
				this.email = email;
			}else{
				throw new RuntimeException("email is not valid");
			}
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public boolean isActive() {
			return active;
		}

		public void setActive(boolean active) {
			this.active = active;
		}

		public double getLat() {
			return lat;
		}

		public void setLat(double lat) {
			this.lat = lat;
		}

		public double getLng() {
			return lng;
		}

		public void setLng(double lng) {
			this.lng = lng;
		}

		

}

