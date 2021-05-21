package twins;

import twins.boundaries.BookBoundary;
import twins.boundaries.Items;

public class Books {

    String kind;
    int totalItems;
    Items[] items;

    public Books() {

    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public Items[] getItems() {
        return items;
    }

    public void setItems(Items[] items) {
        this.items = items;
    }
}
