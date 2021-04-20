package twins.boundaries;

import java.util.Date;
import java.util.HashMap;

public class ItemBoundry {
    private ItemId itemId;
    private String type = "book";
    private String name;
    private boolean active;
    private Date createdTimestamp;
    private createdBy createdBy;
    private location location;
    private HashMap<String, Object> itemAttributes;

    public ItemBoundry() {
        this.createdTimestamp = new Date();
        this.itemAttributes = new HashMap<>();
    }

    public ItemBoundry(ItemId itemId, String name, boolean active, createdBy createdBy) {
        this();
        this.itemId = itemId;
        this.name = name;
        this.active = active;
        this.createdBy = createdBy;
    }

    public ItemId getItemId() {
        return itemId;
    }

    public void setItemId(ItemId itemId) {
        this.itemId = itemId;
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

    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public createdBy getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(createdBy createdBy) {
        this.createdBy = createdBy;
    }

    public location getLocation() {
        return location;
    }

    public void setLocation(location location) {
        this.location = location;
    }

    public HashMap<String, Object> getItemAttributes() {
        return itemAttributes;
    }

    public void setItemAttributes(HashMap<String, Object> itemAttributes) {
        this.itemAttributes = itemAttributes;
    }
}
