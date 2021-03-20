package com.example.demo.sprint2;

import java.util.Date;
import java.util.HashMap;

public class ItemBoundry {
    private itemId itemId;
    private String type;
    private String name;
    private boolean active;
    private Date currentTimestamp;
    private createdBy createdBy;
    private location location;
    private HashMap<String, Object> itemAttributes;

    public ItemBoundry() {
        this.currentTimestamp = new Date();
        this.itemAttributes = new HashMap<>();
    }

    public ItemBoundry(itemId itemId, String type, String name, boolean active, createdBy createdBy) {
        this();
        this.itemId = itemId;
        this.type = type;
        this.name = name;
        this.active = active;
        this.createdBy = createdBy;
    }

    public itemId getItemId() {
        return itemId;
    }

    public void setItemId(itemId itemId) {
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

    public Date getCurrentTimestamp() {
        return currentTimestamp;
    }

    public void setCurrentTimestamp(Date currentTimestamp) {
        this.currentTimestamp = currentTimestamp;
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
