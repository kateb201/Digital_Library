package twins.boundaries;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ItemBoundry implements Cloneable {
    private ItemId itemId;
    private String type = "book";
    private String name;
    private boolean active;
    private Date createdTimestamp;
    private CreatedBy createdBy;
    private Location location;
    private Map<String, Object> itemAttributes;

    public ItemBoundry() {
        this.createdTimestamp = new Date();
        this.itemAttributes = new HashMap<>();
    }

    public ItemBoundry(ItemId itemId, String name, boolean active, CreatedBy createdBy) {
        this();
        this.itemId = itemId;
        this.name = name;
        this.active = active;
        this.createdBy = createdBy;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
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

    public CreatedBy getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(CreatedBy createdBy) {
        this.createdBy = createdBy;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Map<String, Object> getItemAttributes() {
        return itemAttributes;
    }

    public void setItemAttributes(Map<String, Object> itemAttributes) {
        this.itemAttributes = itemAttributes;
    }
}
